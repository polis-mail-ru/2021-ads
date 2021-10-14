# Алгоритмы и структуры данных
Задачи для курса "Алгоритмы и структуры данных" 2021 года
[курса](https://polis.mail.ru/curriculum/program/discipline/836/) в [Технополис](https://polis.mail.ru).
[слайды с лекций](https://cloud.mail.ru/public/UZs7/cF19rRFNW)

## Fork
[Форкните проект](https://help.github.com/articles/fork-a-repo/), склонируйте и добавьте `upstream`:
```
$ git clone git@github.com:<username>/2021-ads.git
Cloning into '2021-ads'...
...
$ git remote add upstream git@github.com:polis-mail-ru/2021-ads.git
$ git fetch upstream
From github.com:polis-mail-ru/2021-ads
 * [new branch]      master     -> upstream/master
```

## Схема работы
В общем случае часть задач будет с [e-olymp](https://www.e-olymp.com), [infromatics-msk](https://informatics.msk.ru/course/view.php?id=4424) и проверяться будет средствами этой системы.
Но также возможны и задачи, тесты на которые будут оформлены в нашем репозитории.

### Решения задач в тестирующей системе
Первым делом регистрируемся на [e-olymp](https://www.e-olymp.com) и на [informatics-msk](https://informatics.msk.ru), на informatics-msk записываемся на [курс](https://informatics.msk.ru/course/view.php?id=4424)

Для каждого нового домашнего задания заводим новую ветку в своем репозитории.
Например, домашнему заданию после первой лекции будет соответствовать ветка `part1`.
Создаем ее в локальном репозитории
```
$ git checkout -b part1
``` 
* Исходники решений добавляются в java-пакет `ru.mail.polis.ads.<partX>.<username>`, где `username` - логин на Github в случае e-olimp или тестов в репозитории.
* В случае с informatics решения добавляются в корень проекта, так как тестирующая система требует класс без указания пакета.

После того, как решения будут доведены до рабочего состояния (все тесты будут проходить),
можно коммитить, пушить и создавать pull request в `polis-mail-ru/2021-ads`.
В самом PR либо в его описании, либо в комментариях к каждому классу-решению
Решение каждой задачи - отдельный Java-класс. Можно воспользоваться классом `ru.mail.polis.ads.SolveTemplate`, в котором остается реализовать лишь метод `solve`.

* E-olymp: В самом PR либо в его описании, либо в комментариях к каждому классу-решению нужно добавить ссылку на submission в e-olymp, где видно, что все решение прошло все тесты. 
Эти ссылки имеют вид "https://www.e-olymp.com/ru/submissions/5707028".
* informatics-msk: добавлять ничего не нужно, статус решения отображается в общих результатах.

Все обсуждения решения происходят в рамках комментариев к PR
(в противном случае мы зафлудим общий чатик и запутаемся окончательно :))

### Решения задач с локальными тестами
Прогон тестов будет осуществляться системами [continuous integration](https://en.wikipedia.org/wiki/Continuous_integration), 
например, [TravisCI](https://travis-ci.org) и/или [CircleCI](https://circleci.com). 
Тесты в этих системах будут исполняться при созданни PR и при добавлении новых коммитов.
В итоге у PR должна появиться зеленая галочка, говорящая об успешном прохождении тестов.

## ДЗ 1.
Задачи с informatics-msk: https://informatics.msk.ru/mod/statements/view.php?id=64802#1

В задачах на реализацию структур данных и выполнение команд на ней подразумевается, что вы не будете использовать Stack/Queue из Java.
В "олимпиадных" задачах можно использовать стандартные реализации java.util.Queue какие найдете, java.util.Stack использовать нельзя.

За каждое полностью рабочее решение дается 2 балла (да, даже за первую задачу).

## ДЗ 2.
Задачи с informatics-msk: https://informatics.msk.ru/mod/statements/view.php?id=65194#1

Во всех задачах запрещается использовать готовые реализации алгоритмов из JDK. В задаче A необходимо написать функцию сортировки, которая либо принимает в качестве аргумента `Comparator` помимо массива, либо принимает массив элементов, реализующих интерфейс `Comparable`.

За каждое полностью рабочее и отвечающее вышеизложенным требованиям решение дается 2 балла

## ДЗ 3.
Задачки с e-olymp.

Дэдлайн - 22.10
  * https://www.e-olymp.com/ru/problems/3737 - Куча ли?
  * https://www.e-olymp.com/ru/problems/4039 - Хипуй
  * https://www.e-olymp.com/ru/problems/4074 - Найти медиану 2
  * https://www.e-olymp.com/ru/problems/10166 - Max куча
  * https://www.e-olymp.com/ru/problems/3738 - Простая сортирока - реализовать HeapSort

## ДЗ 4. Динамическое программирование
Задачки с e-olymp.
Дэдлайн - 22.10

* https://www.e-olymp.com/ru/problems/1087 - Скобочная последовательность
* https://www.e-olymp.com/ru/problems/15 - Мышки и зернышки
* https://www.e-olymp.com/ru/problems/1618 - Наибольшая общая подпоследовательность
* https://www.e-olymp.com/ru/problems/262 - Лесенка
* https://www.e-olymp.com/ru/problems/4261 - Количество инверсий
