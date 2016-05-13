job('hello-world') {

	description '''
		<b>This job is managed by the build-gate in <code>jobs/hello_world.groovy</code>, DO NOT MODIFY</b>
	'''

	parameters {
		stringParam 'QA_GATE', 'ssh://git@git/var/dlpx-qa-gate',
		//stringParam 'QA_GATE', 'git@github.com:j3rrywan9/job-dsl-study.git'
				'OPTIONAL: An alternate QA gate that will be used for the crash-dumper build'
		stringParam 'QA_BRANCH', 'master',
				'OPTIONAL: An alternate QA branch that will be used for the crash-dumper build (default is master)'
	}

	scm {
		git('$QA_GATE', '$QA_BRANCH')
		// github('j3rrywan9/job-dsl-study')
	}

	wrappers {
		colorizeOutput()
		preBuildCleanup {}
		timestamps()
	}

	steps {
		shell 'rm -rf dlpx-qa-gate'
		shell 'git clone --depth 1 --branch $QA_BRANCH "$QA_GATE" dlpx-qa-gate'
	}
}
