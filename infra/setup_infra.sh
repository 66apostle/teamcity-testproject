cd ..

teamcity_tests_directiry=$(pwd)
workdir="teamcity_tests_infrastructure"
teacity_server_workdir="teamcity_server"
teamcity_agent_workdir="teamcity_agent"
selenoid_workdir="selenoid"
teamcity_server_container_name="teamcity_server_instance"
teamcity_agent_container_name="teamcity_agent_instance"
selenoid_container_name="selenoid_instance"
selenoin_ui_container_name="selenoid_ui_instance"

workdir_names=($teamcity_teacity_server_workdir $teamcity_agent_workdir $selenoid_workdir)
container_names=($teamcity_server_container_name $teamcity_agent_container_name $selenoid_container_name $selenoin_ui_container_name)

###################################################################################
echo "Request IP"

export ips=$(ipconfig | grep "IPv4 Address" | awk -F: '{ print $2 }' | tr -d ' ')
export ip=${ips%%$'\n'*}
echo "Current IP = $ip"

###################################################################################
echo "Delete previous run data"

rm -rf $workdir
mkdir $workdir
cd $workdir

for dir in "${workdir_names[@]}"; do
  mkdir $dir
  done

for container in "${container_names[@]}"; do
  docker stop $container
  docker  rm $container
  done
echo "Previous run data deleted"

###################################################################################
echo "Start teamcity-server"

cd $teacity_server_workdir

docker run --name teamcity-server-instance \
    -v $(pwd)/datadir:/data/teamcity_server/datadir \
    -v $(pwd)/logs:/opt/teamcity/logs \
    -p 8111:8111 \
    jetbrains/teamcity-server
echo "Server started"


###################################################################################
echo "Start selenoid"

cd .. && cd $selenoid_workdir
mkdir config
cp $teamcity_tests_directiry/infra/browsers.json config/

docker run -d --name selenoid \
    -p 4444:4444 \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v $(pwd)/config/:/etc/selenoid/:ro \
    aerokube/selenoid:latest-release

image_names=($(awk -F'"' '/"image": "/{print $4}' "$(pwd)/config/browsers.json"))

for image in "${image_names[@]}"; do
  docker pull $image
done
echo "Selenoid started"

###################################################################################
echo "Start selenoid-ui"

docker run -d --name selenoid-ui \
    -p 8080:8080 \
    aerokube/selenoid-ui \
    --selenoid-uri http://$ip:4444
echo "Selenoid UI started"

###################################################################################
echo "Setup teamcity-server"

cd .. && cd ..
mvn clean test -Dtest=SetupTest#startUpTest
echo "Server configured"

###################################################################################
echo "Parse superuser token"

superuser_token=$(grep -o 'Super user authentication token: [0-9]*' $teamcity_tests_directory/infra/$workdir/$teamcity_server_workdir/logs/teamcity-server.log | awk '{print $NF}')
echo "Token parsed"

###################################################################################
echo "Run system tests"

echo "host=$ip:8111\nsuperUserToken=$superuser_token\nremote=http://$ip:4444/wd/hub\nbrowser=firefox" > $teamcity_tests_directory/src/main/resources/config.properties
cat $teamcity_tests_directory/src/main/resources/config.properties

###################################################################################

echo "Run API tests"
mvn test -DsuiteXmlFile=testng-suites/api-suite.xml

###################################################################################

echo "Run UI tests"
mvn test -DsuiteXmlFile=testng-suites/ui-suite.xml