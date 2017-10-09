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
package helpers;

import com.google.gson.Gson;
import entities.Repositories;
import entities.RepositoryItem;
import pages.RepositoriesPage;
import ru.alfabank.alfatest.cucumber.api.AkitaScenario;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class GitHubSearchPageHelper {

    AkitaScenario akitaScenario = AkitaScenario.getInstance();

    public LinkedList<String> getRepoListNamesFromPage() {
        RepositoriesPage repositoriesPage = akitaScenario.getPage(RepositoriesPage.class);
        return repositoriesPage.getRepoNames().stream()
                .map(item -> item.getText().trim())
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<String> getRepoListNamesFromResponse(Repositories repositories) {
        return repositories.getItems().stream()
                .map(RepositoryItem::getFullName)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public Repositories getRepositoriesFromResponse(String response) {
        return new Gson().fromJson(response, Repositories.class);
    }

}
