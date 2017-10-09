/**
 * Copyright 2017 Alfa Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package steps;

import cucumber.api.java.ru.Тогда;
import entities.Repositories;
import helpers.GitHubSearchPageHelper;
import lombok.extern.slf4j.Slf4j;
import ru.alfabank.alfatest.cucumber.api.AkitaScenario;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;

@Slf4j
public class TemplateSteps {
    AkitaScenario akitaScenario = AkitaScenario.getInstance();
    GitHubSearchPageHelper gitHubSearchPageHelper = new GitHubSearchPageHelper();

    @Тогда("^список репозиториев на странице соответствует ответу сервиса из переменной \"([^\"]*)\"$")
    public void checkReposListOnPageCorrespondsToResponse(String response) throws Throwable {
        String responseAsString = akitaScenario.getVar(response).toString();
        Repositories repositories = gitHubSearchPageHelper.getRepositoriesFromResponse(responseAsString);
        List<String> repoNamesFromPage = gitHubSearchPageHelper.getRepoListNamesFromPage();
        List<String> repoNamesFromResponse = gitHubSearchPageHelper.getRepoListNamesFromResponse(repositories);
        assertThat(format("список со страницы [%s] не соответствует списку из ответа сервиса [%s]", repoNamesFromPage, repoNamesFromResponse), repoNamesFromPage.toArray(), arrayContainingInAnyOrder(repoNamesFromResponse.toArray()));
    }
}
