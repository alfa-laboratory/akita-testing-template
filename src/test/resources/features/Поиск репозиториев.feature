#language:ru

Функционал: Поиск репозиториев в github

  Структура сценария: Поиск репозиториев в github по ключевому слову
    Пусть совершен переход на страницу "Страница входа GitHub" по ссылке "gitHubMainPage"
    И в поле "Поиск" введено значение "<searchQuery>"
    И выполнено нажатие на клавиатуре "Enter"
    Тогда страница "Репозитории" загрузилась
    Когда выполнен GET запрос на URL "{repositorySearchUrl}<searchQuery>". Полученный ответ сохранен в переменную "<searchResultVariable>"
    Тогда список репозиториев на странице соответствует ответу сервиса из переменной "<searchResultVariable>"

    Примеры:
      | searchQuery | searchResultVariable     |
      | alfalab     | repositoriesSearchResult |
      | alfalab2    | repositoriesSearchResult |


  Сценарий: Отображение страницы входа GitHub
    Дано совершен переход на страницу "Страница входа GitHub" по ссылке "gitHubMainPage"
    Тогда страница соответствует спецификации "welcomepage.spec" для экрана "desktop"
