# How to contribute to OpenML

This repository is set up to work under the traditional fork + Pull Request model.

## Architecture guidelines

Development of these artifacts should be done with care and taking into account that these APIs are implemented by multiple providers. As much as possible changes should be done in a gradual way, with older endpoints being deprecated in one Major.Minor release and eliminated in the next.

Also, note that the model implementations are called in execution contexts where throughput could be high and latency is critical. Take this into account when choosing data structures and the levels of abstraction used.

## CI/CD
Quality is validated in pull requests using [travis-ci.com](https://travis-ci.com/feedzai/feedzai-openml) using the configuration that you can see in the [Travis YAML](https://github.com/feedzai/feedzai-openml/blob/master/.travis.yml).
The `script` phase of the Travis lifecycle will run tests which produce JaCoCo coverage reports that are in turn sent to [Codecov.io](https://codecov.io) for code coverage analysis.
In parallel, Codacy runs static code analysis and displays it on [Codacy](https://app.codacy.com/app/feedzai/feedzai-openml/dashboard).

This project uses Mutation Testing to ensure the quality of the testing suite. If your contribution is failing on that step, you should probably read the context and explanation available on the PR which introduced the technology: https://github.com/feedzai/feedzai-openml/pull/33.

## Merging
Pull requests with failing builds will not be merged, and coverage is expected to be above 85%.
Static code analysis issues will be evaluated ad-hoc, especially since it is common to have several warnings related to abstraction violations that need to be performe