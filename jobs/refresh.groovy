multiJob('refresh') {
    description '''
		<b>This job is managed by the build-gate in <code>jobs/refresh.groovy</code>, DO NOT MODIFY</b>
	'''

    parameters {
        stringParam 'QA_GATE', 'ssh://git@git/var/dlpx-qa-gate'
                'OPTIONAL: An alternate QA gate that will be used for the crash-dumper build'
        stringParam 'QA_BRANCH', 'master',
                'OPTIONAL: An alternate QA branch that will be used for the crash-dumper build (default is master)'
    }

    scm {
        git('$QA_GATE', '$QA_BRANCH')
        //github('j3rrywan9/job-dsl-study')
    }

    steps {
        shell 'rm -rf dlpx-qa-gate'
        shell 'git clone --depth 1 --branch $QA_BRANCH "$QA_GATE" dlpx-qa-gate'
    }
}
