multiJob('test-groovy-postbuild') {
  description '''
    Test Groovy postbuild plugin.
    '''.stripIndent()

  parameters {
    stringParam 'ELAPSED_TIME', '10', 'Elapsed time.'
  }

  wrappers {
    colorizeOutput()
    timestamps()
  }

  steps {
    shell 'sleep 1'
  }

  publishers {
    groovyPostBuild {
      script '''
        import hudson.model.*

        def environment = manager.build.getEnvVars()

        duration = manager.build.getTimeInMillis()

        manager.listener.logger.println("Increasing ELAPSED_TIME from " + environment["ELAPSED_TIME"] + " to " + duration)

        def oldPa = manager.build.getAction(ParametersAction.class)

        if (oldPa != null) {
          manager.build.actions.remove(oldPa)
          newPa = oldPa.createUpdated([new StringParameterValue("ELAPSED_TIME", Long.toString(duration))])
        }

        manager.build.actions.add(newPa)
        '''.stripIndent()
    }

    downstreamParameterized {
      trigger('echo-elapsed-time') {
        condition('SUCCESS')
        parameters {
          predefinedProp('ELAPSED_TIME', '${ELAPSED_TIME}')
        }
      }
    }
  }
}

multiJob('echo-elapsed-time') {
  description '''
    Echo elapsed time for upstream job
  '''.stripIndent()

  parameters {
    stringParam 'ELAPSED_TIME', '10', 'Elapsed time.'
  }

  wrappers {
    colorizeOutput()
    timestamps()
  }

  steps {
    shell 'echo ${ELAPSED_TIME}'
  }
}
