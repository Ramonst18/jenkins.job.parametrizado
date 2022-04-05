job('ejemplo2-job-DSL'){
    description('Job DSL de ejemplo para el curso de Jenkins')
    scm {
        git('https://github.com/Ramonst18/jenkins.job.parametrizado.git', 'main') { node ->
      	    node / gitConfigName('Ramonst18')
      	    node / gitConfigEmail('Ramonst18@hotmail.com')
	}
    }
    parameters{
        stringParam('nombre', defaultValue = 'Julian', description = 'Parametro de cadena para el Job')
  	choiceParam('planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
    	booleanParam('agente', false)
    }
    triggers{
        cron('H/7 * * * *')
    }
    steps {
        shell("jobscript.sh")
    }
    publishers{
        mailer("ramonlopezpracticas2@gmail.com", true, true)
        slackNotifier{
            notifyAborted(true)
            notifyEveryFailure(true)
            notifyNotBuilt(false)
            notifyUnstable(false)
            notifyBackToNormal(true)
            notifySuccess(false)
            notifyRepeatedFailure(false)
            startNotification(false)
            includeTestSummary(false)
            includeCustomMessage(false)
            customMessage(null)
            sendAs(null)
            commitInfoChoice('NONE')
            teamDomain(null)
            authToken(null)
	}
    }
}
