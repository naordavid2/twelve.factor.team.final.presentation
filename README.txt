There are some demos in this repo and tutorials.
This is recommended to use the installation part in the tutorials in order to prepare your environment for the integrated demo

For the integrated demo you should:
1. Have MongoDb installed.
2. Have ElasticSearch installed.
3. Have Kibana installed.
4. Have LogStash installed.
5. Configure Environment Variables as in the tutorial of "Centralized Configuration" (Do this before you run eclipse or restart eclipse if open):
	EUREKA_SERVER_URI = http://localhost:9090/eureka
	CONFIG_SERVER_URI = http://localhost:9099

Then run the follow in the same order:
1. Run MongoDb: 		[MongoDb location]\bin\mongod.exe
2. Run ElasticSearch: 	[ElasticSearch location]\bin\elasticsearch
3. Run Kibana: 			[Kibana location]\bin\kibana.bat
4. Run LogStash: 		[LogStash location]\bin\logstash -f logDemo.conf
5. Run ServerConfigDemoApplication 		from the project demo.config.server (this will run on port 9099)
6. Run ServerRegistrationDemo 			from the project demo.registration.server (this will run on port 9090)
7. Run ReadersApplication 				from the project reader.repository (you should run multiple instances - random ports)
8. Run ThreeFactorsOfTwelveApplication 	from the project demo.microservice.three.factors.of.twelve (this will run on port 9095)


URLS to run on your browser:
Eureka:
http://localhost:9090/

Kibana:
http://localhost:5601

Three of Twelve
http://localhost:9095/hello
http://localhost:9095/hello/readers
http://localhost:9095/hello/readers?size=1&page=0
http://localhost:9095/hello/readers?size=1&page=1
http://localhost:9095/readers/1/1@gmail.com/read1/read1last
http://localhost:9095/readers/2/2@gmail.com/read2/read2last

