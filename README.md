# VkRubricMaker
Console client for making body of rubrics the "Questions" and the "Loss".

#### Справка

При первом запуске скрипта в папке, откуда он был запущен, создается файл path.txt, две папки - json и output, и два файла в папке json - questions.json и loss.json. В файл path.txt можно добавить свой путь для хранения папок с выходными файлами скрипта. Папка json хранит файлы с первичными версиями добавляемых постов. Папка output хранит файлы с данными, готовыми к использованию вне скрипта.

##### Файл path.txt

Указываемый путь должен быть абсолютным (начиная от корня системы). Файл должен лежать рядом с файлом скрипта. Для того, чтобы скрипт мог прочитать файл path.txt, его [скрипт] необходимо запускать из папки, где он хранится.

##### Команды скрипта

###### COMPUTER [Main menu]: 1 == Add post

Открывает меню с командами для добавления нового поста.

###### COMPUTER [Main menu -> Add post]: 1 == Add question.

Запускает алгоритм создания нового вопроса.

###### USER [.. -> Add questions -> Body]: (-/00) 

Текстовая часть вопроса. Для добавления необходимо скопировать нужный текст в буфер обмена и нажать Enter. Для отмены создания поста и возврата в меню добавления новых постов необходимо ввести "00" (без кавычек) и нажать Enter.

###### USER [.. -> Add questions -> Photo]: (0-10/00)

Алгоритм добавления фотографий. На данном этапе необходимо указать количество изображений от 1 до 10. После ввода количества, равного 0, процесс добавления фотографий будет пропущен, и запустится следующий алгоритм. ля отмены создания поста и возврата в главное меню необходимо ввести "00" (без кавычек) и нажать Enter.

###### USER [.. -> Add photo -> Photo №1]: 

Алгоритм добавления фотографий. На данном этапе необходимо скопировать ссылку на изображение в буфер обмена и нажать Enter. 

###### USER [.. -> Add questions -> Author]: (0-1/00) 

Алгоритм добавления подписи автора. На данном этапе необходимо указать наличие подписи путем ввода цифры 1 или 0 (с подписью и без подписи соответственно). После ввода цифры 0 процесс добавления подписи автора будет пропущен, а запустится следующий алгоритм. Для отмены создания поста и возврата в меню добавления новых постов необходимо ввести "00" (без кавычек) и нажать Enter.

###### USER [.. -> Author -> URL] (-/00) 

Алгоритм добавления подписи автора. На данном этапе необходимо скопировать ссылку в буфер обмена на страничку автора в социальной сети ВКонтакте и нажать Enter. Для отмены создания поста и возврата в меню добавления новых постов необходимо ввести "00" (без кавычек) и нажать Enter.

###### USER [.. -> Author -> Full name] (-/00) 

Алгоритм добавления подписи автора. На данном этапе необходимо скопировать полное имя автора поста в буфер обмена и нажать Enter. Для отмены создания поста и возврата в меню добавления новых постов необходимо ввести "00" (без кавычек) и нажать Enter.

###### USER [.. -> Add post -> Write post]: (1/0) 

Алгоритм сохранения поста в JSON-файл. На данном этапе необходимо подтвердить или отменить сохранение добавленных данных в файл путем ввода цифры 1 или 0 соответственно.

###### COMPUTER [Main menu -> Add post]: 2 == Add loss.

Запускает алгоритм добавления нового объявления о потере или находке. Процесс аналогичен процессу добавления нового вопроса.

###### COMPUTER [Main menu -> Add post]: 0 == Step back.

Возврат в главное меню.

###### COMPUTER [Main menu]: 2 == Lists menu.

Переход в меню просмотра списка категорий.

###### COMPUTER [Main menu -> Lists menu]: 1 == Show list of questions.

Просмотр списка добавленных вопросов.

###### COMPUTER [Main menu -> Lists menu]: 2 == Show list of loss.

Просмотр списка добавленных объявлений о потерях и находках.

###### COMPUTER [Main menu]: 3 == File manager.

Переход в меню работы с файлами.

###### COMPUTER [Main menu -> File manager]: 1 == Clear files.

Очистка списков с вопросами и объявлениями о потерях и находках.

###### USER: [.. -> Clear files] (1/0) 

Подтверждение очистки файла. 1 - подтверждение, 0 - отмена.

###### COMPUTER [Main menu -> File manager]: 2 == Export files.

Вывод списков с вопросами и объявлениями о потерях и находках из JSON-файла в текстовый файл, для последующего копирования в пост-рубрику.

###### USER: [.. -> File manager -> Export files] (1/0) 

Подтверждение экспорта файлов. 1 - подтверждение, 0 - отмена.

###### COMPUTER [Main menu]: 0 == Close program.

Выход из программы.
