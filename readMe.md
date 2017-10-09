<img align="left" width="140" height="140" title="akita"
     src="https://github.com/alfa-laboratory/akita/blob/master/akita.png" />

# Awesome testing with Akita
<br><br>
 
## Предварительные настройки

**1. Проверить,что установлена Java 8**

**2. Необходимо установить следующие плагины для Intellij IDEA:**

* Cucumber for Java
* Gherkin
* Lombok

**3. Настроить плагин Lombok:**

* в разделе *Build, Execution, Deployment -> Compiler -> Annotation Processors* требуется поставить галочку *Enable annotation processing*
* в разделе *Other settings -> Lombok plugin* требуется поставить галочку *Enable lombok plugin for this project*

**4. Скачать webdriver нужной версии**

Важно, чтобы версия драйвера соответствовала версии браузера.
Узнать о том, какая версия нужна тебе для браузера Chrome, и скачать ее можно с ресурса https://sites.google.com/a/chromium.org/chromedriver/downloads

**5. Прописать путь в переменную PATH до папки с драйверами**

В зависимости от ОС выполни
```
  echo %PATH%
  или
  echo $PATH
```

 Для *Windows* выполни в папке, где сохранен драйвер, команду:
```
    setx PATH "%PATH%;%CD%"
 ```

 Для *Linux* и *Mac OS*  просто скачай драйверы для Chrome или Safari и пропиши путь до папки при необходимости:
 ```
  > gedit .bash_profile
  > PATH = /usr/local/bin/drivers:$PATH
  > source .bash_profile
 ```

Напишем первый тест?
--------------------

#### Структура проекта

* папка **gradle** содержит в себе все необходимое для работы градловых команд.
   Самое важное здесь:
   distributionUrl=https://services.gradle.org/distributions/gradle-4.2.1-all.zip
* файл **build.gradle** содержит  информацию о всех подгружаемых библиотеках и плагинах для нашего тестового проекта
Плагины для запуска тестов и генерации отчета: 
```
   classpath 'ru.alfalab.gradle:cucumber-reporting-gradle-plugin:0.1.0'
   classpath 'ru.alfalab.gradle:cucumber-parallel-test-gradle-plugin:0.2.1' 
```
про особенности используемых плагинов можно почитать на github тут:
> https://github.com/alfa-laboratory/cucumber-reporting-gradle-plugin;
> https://github.com/alfa-laboratory/cucumber-parallel-test-gradle-plugin; <br>

В данном месте указывается пакет, где должны лежать классы с новыми пользовательскими шагами:
```
   generateRunner.glue = ["ru.alfabank.steps", "steps"]
```
* параметры по умолчанию для gradle задач указываются в **gradle.properties**
* файл **settings.gradle** содержит только название проекта
* пакет **src/main/java** содержит ваши page object-ы, реализацию шагов, сущности для интеграционных тестов и другие дополнительные классы.
* пакет **src/main/java/restBodies** используется для хранения файлов с телом запросов. На данную папку жестко привязан  шаг отправки запроса.
* директория  **src/test** предназначена только для хранения feature-файлов с тестами.

### Общие шаги
 Все базовые шаги находятся в библиотеке в классах DefaultSteps, DefaultApiSteps. Вы можете пользоваться этими шагами при составлении новых сценариев.
 
### Пользовательские шаги
 Если есть необходимость реализовать свои специфичные шаги, это можно сделать, создав новый класс в пакете src/main/java/steps. 

### Как воспользоваться возможностями akitaScenario?
Для доступа к общим методам сценариев, потребуется в каждом классе шагов прописать вот такую строку.

> Akita akitaScenario = Akita.getInstance();

Для работа с новыми стораницами необходимо в классе страницы унаследоваться от AkitaPage:

> extends AkitaPage

### Время загрузки страницы
Реализована возможность управления временем ожидания появления элемента на странице.
Чтобы установить timeout, отличный от базового, нужно добавить в **application.properties**
строку
> waitingAppearTimeout=150000

### Отображение в отчете справочной информации
Для того, чтобы в отчете появился блок  Output с информацией, полезной для анализа отчета, можно воспользоваться следующим методом

> akitaScenario.write("Текущий URL = " + currentUrl + " \nОжидаемый URL = " + expectedUrl);

#### Запуск тестов
Скачанный проект с тестами нужно открыть как gradle-проект, далее ты можешь запускать тесты одним
из удобных тебе способов
* gradle задачи:  **Clean**, **Test**, **GenerateCucumberReport**
* контекстное меню для конкретного feature-файла, где следует не забывать про установку VM Properties.
  -Dbrowser=chrome
* из терминала
 ```
   > gradlew clean test -i
   > gradlew clean generateCucumberReport --debug
   > gradlew clean test -Pbrowser=chrome -Pprofile=local -PremoteHub=http://remote/wd/hub
   > gradlew clean test -Dbrowser=chrome -Ptag=@test
  ```

  Для проектов предусмотрен параллельный запуск всех feature-файлов (для каждого feature-файла создается свой runner), запуск тестов на удаленной машине (-PremoteHub), смена бразера (-Pbrowser=chrome), запуск тестов согласно выставленным на сценариях тегам (-Ptag).
   
### Отчет о тестировании
   Akita позволяет генерировать красивые отчеты о прошедщем тестировании.
   Генерация отчета осуществляется запуском команды
   ```
   gradlew generateCucumberReport        или gradlew gCR
   ```

Отчет создается в папке  **build/reports**.  Основным является файл **"overview-features.html"**.

# Особенности 
1. Важно!!! Плагин, используемый нами для запуска тестов завязан на путь до feature-файлов  src/test/resources/features. Не рекомендуется его изменять.


