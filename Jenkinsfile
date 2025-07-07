#!groovy

node('SHARED&&WINDOWS64') { // fix on node HOCI_BUILD because can access shared dir only via axiaz
  properties([
            buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '10')),
            disableConcurrentBuilds(),
            disableResume(),
            parameters([string(description: 'AWS KMS Key ARN',
                                      name: 'aws_key_arn',
                                      trim: true,
                                      defaultValue: 'arn:aws:kms:your-aws-region:012345678901:key/your-key-id'),
                        choice(choices: ['RSA_4096', 'ECC_NIST_P256'], name: 'aws_key_spec'),
                        string(description: 'certificate common name (CN)',
                                      name: 'cert_common_name',
                                      trim: true,
                                      defaultValue: 'examplecorp.com'),
                        string(description: 'country code (C)',
                                      name: 'cert_country_code',
                                      trim: true,
                                      defaultValue: 'US'),
                        string(description: 'The AWS access key defined in Jenkins' secrets.',
                                      name: 'AWS_ACCESS_KEY',
                                      trim: true,
                                      defaultValue: 'max-schaefer-iam')])
  ])
  env.JENKINS_MAVEN_AGENT_DISABLED = 'true'
  ws {
    cleanWs()
    try {
      checkout scm
      def kmscsrJson = [
                          "aws_key_arn": "${params.aws_key_arn}",
                          "aws_key_spec": "${params.aws_key_spec}",
                          "cert_common_name": "${params.cert_common_name}",
                          "cert_country_code": "${params.cert_country_code}",
                        ]

      writeJSON file: 'cfg/kmscsr.json', json: kmscsrJson, pretty: 2

      withMaven(maven: 'Maven 3.9.x', jdk: 'OpenJDK 17.x 64 bits') {
        bat "mvn -B clean compile -Daws_key_arn=%params.aws_key_arn% -Daws_key_spec=%params.aws_key_spec% -Dcert_common_namec=%params.cert_common_name% -Dcert_country_code=%params.cert_country_code%"
        withCredentials([[ $class: 'AmazonWebServicesCredentialsBinding', credentialsId: params.AWS_ACCESS_KEY]]){
          bat "mvn exec:java -Daws.accessKeyId=%AWS_ACCESS_KEY_ID% -Daws.secretAccessKey=%AWS_SECRET_ACCESS_KEY%"
        }
      }
    } finally {
      cleanWs()
    }
  }
}