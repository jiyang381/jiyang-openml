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
Static code analysis issues will be evaluated ad-hoc, especially since it is common to have several warnings related to abstraction violations that need to be performed for performance reasons.

When merging bug fixes, maintainers will **cherry pick the changes to the appropriate hotfix branches**.

## Releasing
**When releasing a new Major.Minor** (read about [Semantic Versioning](https://semver.org/)) maintainers need perform a few steps to ensure the repository documents are updated:
   * Update the [README](https://github.com/feedzai/feedzai-openml/blob/master/README.md) on the *master* branch so that the XML snippets indicate the new version. Also, update the maven archetype command version.
   * Create a new hotfix branch named *hf-MM.mm.X* (where *MM* is the Major and *mm* the Minor)
   * In that new branch, perform a pull request to change the badge URLs to point to the new branch
   * Make the branch protected in the settings, so that force pushes are not allowed and PRs are mandatory
   * Consider adding release notes specifying the most relevant changes. Take as an example: https://github.com/feedzai/feedzai-openml/releases/tag/0.2.0
   * Consider deprecating existing maintenance branches, by [freezing](https://help.github.com/articles/enabling-branch-restrictions/) those branches, after synchronizing with current maintainers

**When releasing a new hotfix version** maintainers need to perform a smaller set of steps:
  * Update the [README](https://github.com/feedzai/feedzai-openml/blob/master/README.md) on the corresponding hotfix branch so that the XML snippets indicate the new version. Additionally ensure that the [README](https://github.com/feedzai/feedzai-openml/blob/master/README.md) on the *master* branch is updated with the latest `Major.Minor.hotfix` branch (this means that only if you are releasing an hotfix on the latest `Major.Minor` released version, then you need to update *master* branch also).
  * Consider adding release notes specifying the most relevant changes. Take as an example: https://github.com/feedzai/feedzai-openml-r/releases/tag/0.2.0

**For all releases**, as the hotfix branch is ready all that's needed to actually release is to create an annotated tag pointing to the hotfix branch head (example below for releasing version `1.1.0`):

```bash
# Ensure the tag is made on the udpated branch
git fetch -a
git checkout origin/hf-1.1.X
git tag -a 1