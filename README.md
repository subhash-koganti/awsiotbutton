# Build Command 

  ## Use the below Command to Build the Lambda Jar that needs to be uploaded to AWS lambda . This commands packages dependencies also in to the fat jar.

	
	mvn assembly:assembly -DdescriptorId=jar-with-dependencies package
