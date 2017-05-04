# coding: utf8


import gtk
import json


def starter():
    try:
        open("json/questions.json", 'r')
    except IOError as var_except:
        file = open("json/questions.json", 'w')
        file.write("{}")
        file.close()

        print(
            "COMPUTER: Error, " + str(var_except) +
            ". Was created file \"questions.json\".")
    except Exception as var_except:
        print(
            "COMPUTER: Error, " + str(var_except) + ". Exit from program...")
        exit()

    try:
        open("json/loss.json", 'r')
    except IOError as var_except:
        file = open("json/loss.json", 'w')
        file.write("{}")
        file.close()

        print(
            "COMPUTER: Error, " + str(var_except) +
            ". Was created file \"loss.json\".")
    except Exception as var_except:
        print(
            "COMPUTER: Error, " + str(var_except) + ". Exit from program...")
        exit()
    main_menu()


def main_menu():
    print(
        "\n" +
        "COMPUTER: You are in Main menu...")
    print(
        "COMPUTER [Main menu -> ]: 1 == Add post.")
    print(
        "COMPUTER [Main menu -> ]: 2 == Lists menu.")
    print(
        "COMPUTER [Main menu -> ]: 3 == File manager.")
    print(
        "COMPUTER [Main menu -> ]: 0 == Close program.")
    try:
        user_answer = raw_input("USER: [Main menu -> ] ")
        if user_answer == "0":
            print("COMPUTER [Main menu]: Exit from program...")
            exit()
        else:
            if user_answer == "1":
                add_post()
            else:
                if user_answer == "2":
                    lists_menu()
                else:
                    if user_answer == "3":
                        file_manager()
                    else:
                        print(
                            "COMPUTER [Main menu] Unknown command. " +
                            "Retry query...")
                        main_menu()
    except Exception as var_except:
        print(
            "COMPUTER [Main menu]: Error, " + str(var_except) +
            ". Return to Main menu...")
        main_menu()


class Post:
    var_number_category = 0
    var_number_post = 0
    var_body = ""
    var_photo = ""
    var_author_url = ""
    var_author_name = ""

    def set_var_number_category(self, var_number_category_inner):
        self.var_number_category = var_number_category_inner

    def set_var_number_post(self, var_number_post_inner):
        self.var_number_post = var_number_post_inner

    def set_var_body(self, var_body_inner):
        self.var_body = var_body_inner

    def set_list_photo(self, var_photo_inner):
        self.var_photo = var_photo_inner

    def set_var_author_url(self, var_author_url_outher):
        self.var_author_url = var_author_url_outher

    def set_var_author_name(self, var_author_name_outher):
        self.var_author_name = var_author_name_outher

    def get_var_number_category(self):
        return self.var_number_category

    def get_var_number_post(self):
        return self.var_number_post

    def get_var_body(self):
        return self.var_body

    def get_list_photo(self):
        return self.var_photo

    def get_var_author(self):
        var_author = ""
        if self.var_author_url != "" and self.var_author_name != "":
            var_author_url = self.var_author_url[self.var_author_url.rfind('/'):]
            var_author = "*" + var_author_url + " (" + self.var_author_name + ")"
        return var_author

    def get_var_author_url(self):
        return self.var_author_url

    def get_var_author_name(self):
        return self.var_author_name

    def __init__(self):
        self.var_number_category = 0
        self.var_number_post = 0
        self.var_body = ""
        self.var_photo = ""
        self.var_author_url = ""
        self.var_author_name = ""


def read_json(post_type):
    try:
        old_json = json.load(open("json/" + post_type + ".json", 'r'))
        return old_json
    except Exception as var_except:
        print(
            "COMPUTER [Main menu -> Add post -> Add " + post_type +
            " -> Read file]: Error, " + str(var_except) +
            ". Return to menu Add post...")
        add_post()


def add_post():

    def set_number_post(post_type, obj_post, old_json):
        try:

            old_json_map = json.loads(old_json)

            obj_post.set_number_post(len(old_json_map) + 1)

            return obj_post

        except Exception as var_except:
            print(
                "COMPUTER [Main menu -> Add post -> Add " + post_type +
                " -> Number post]: Error, " + str(var_except) +
                ". Return to menu Add post...")
            add_post()

    def set_body(post_type, obj_post):

        try:

            print(
                "\n" +
                "COMPUTER [Main menu -> Add post -> Add " + post_type +
                " -> Body]: Copy text for post and press \"Enter\", or enter \"00\" for cancel.")

            user_answer = raw_input("USER [.. -> Add " + post_type + " -> Body]: ")

            if user_answer == "00":
                "COMPUTER: Cancel..."
                add_post()
            else:
                cb = gtk.clipboard_get()

                text = str(gtk.Clipboard.wait_for_text(cb))

                obj_post.set_var_body(text.decode("utf8"))

                print("\n")
                print(
                    str(obj_post.get_var_number_category()) + "." +
                    str(obj_post.get_var_number_post()) + ") " +
                    obj_post.get_var_body() + str())
                print("\n")

                return obj_post

        except Exception as var_except:
            print(
                "COMPUTER [Main menu -> Add post -> Add " + post_type +
                " -> Body]: Error, " + str(var_except) +
                ". Return to menu Add post...")
            add_post()

    def set_photo(post_type, obj_post):

        try:

            print(
                "\n" +
                "COMPUTER [Main menu -> Add post -> Add " + post_type +
                " -> Photo]: Enter count photo and press \"Enter\", or enter \"00\" for cancel.")

            list_photo = ""

            user_answer = raw_input("USER [.. -> Add " + post_type + " -> Photo]: ")

            if user_answer == "00":
                "COMPUTER: Cancelling..."
                main_menu()
            else:
                if user_answer == "0":
                    return obj_post
                else:
                    if int(user_answer) > 0 and int(user_answer) <= 10:
                        photo_count = int(user_answer)
                        list_photo[photo_count + 1]
                        list_photo[0] = "Фото:"

                        cb = gtk.clipboard_get()

                        for i, nothing in enumerate(list_photo):

                            if i >= 1:

                                print(
                                    "COMPUTER [Main menu -> Add post -> Add " + post_type +
                                    " -> Photo №" + str(i) + "]: Copy URL and press \"Enter\".")

                                raw_input("USER [.. -> Add photo -> Photo №" + str(i) + "]: ")

                                url = str(gtk.Clipboard.wait_for_text(cb))
                                url = url.decode("utf8")

                                print(
                                    "COMPUTER [Main menu -> Add post -> Add " + post_type +
                                    " -> Photo -> №1]: " + str(url))

                                list_photo[i] = "- " + url

                                obj_post.set_photo(list_photo)
                    else:
                        print(
                            "COMPUTER [Main menu -> Add post -> Add " + post_type +
                            ". Photo]: Unknown value. Retry query...")
                        return set_photo

            return obj_post

        except Exception as var_except:
            print(
                "COMPUTER [Main menu -> Add post -> Add " + post_type +
                " -> Photo]: Error, " + str(var_except) +
                ". Retry query...")
            return set_photo(post_type, obj_post)

    def set_author(post_type, obj_post):

        print(
            "\n" +
            "COMPUTER [Main menu -> Add post -> Add " + post_type +
            " -> Author]: Enter \"1\", if need signature, or \"0\", if not. " +
            "Enter \"00\" for cancel.")

        user_answer = raw_input("USER [.. -> Add " + post_type + " -> Author]: ")

        if user_answer == "00":
            "COMPUTER: Cancel..."
            add_post()
        else:
            if user_answer == "0":
                return obj_post
            else:
                if user_answer == "1":
                    try:
                        cb = gtk.clipboard_get()

                        print(
                            "COMPUTER [Main menu -> Add post -> Add " + post_type +
                            "-> Author -> URL] Copy link to author's page and press \"Enter\". " +
                            "Enter \"00\" for cancel.")

                        user_answer = raw_input("USER [.. -> Author -> URL] ")

                        url = ""

                        if user_answer == "00":
                            add_post()
                        else:
                            url = str(gtk.Clipboard.wait_for_text(cb))
                            url = url.decode("utf8")

                            print(
                                "COMPUTER [Main menu -> Add post -> Add " + post_type +
                                "-> Author -> URL] " + url)

                            print(
                                "COMPUTER [Main menu -> Add post -> Add " + post_type +
                                "-> Author -> Full name] " +
                                "Copy author's full name and press \"Enter\". " +
                                "Enter \"00\" for cancel.")

                            user_answer = raw_input("USER [.. -> Author -> Full name] ")

                            name = ""

                            if user_answer == "00":
                                "COMPUTER: Cancel..."
                                add_post()
                            else:
                                name = str(gtk.Clipboard.wait_for_text(cb))
                                name = name.decode("utf8")

                                print(
                                    "COMPUTER [Main menu -> Add post -> Add " + post_type +
                                    "-> Author -> Full name] " + name)

                            obj_post.set_var_author_url(url)
                            obj_post.set_var_author_name(name)

                        return obj_post

                    except Exception as var_except:
                        print(
                            "COMPUTER [Main menu -> Add post -> Add " + post_type +
                            " -> Author]: Error, " + str(var_except) +
                            ". Return to menu Add post...")
                        add_post()
                else:
                    print(
                        "COMPUTER [Main menu -> Add post -> Add " + post_type +
                        "]: Unknown value. Retry query...")
                    return set_author(post_type, obj_post)

        print(
            "COMPUTER [Main menu -> Add post -> Add " + post_type +
            " -> Author]: Copy text for post and press \"Enter\".")

        add_post()

    def write_post(post_type, obj_post, old_json):

        array_json = ""

        if obj_post.get_list_photo() != "":
            array_photo = obj_post.get_list_photo()

            for i, var_photo in array_photo:
                array_json = "[\"" + var_photo + "\","

            array_json = array_json[0:len(array_json - 1)] + "]"

        post_json = {
            "category": obj_post.get_var_number_category(),
            "num": obj_post.get_var_number_post(),
            "body": obj_post.get_var_body(),
            "photo": array_json,
            "author": obj_post.get_var_author()}

        obj_json = json.dumps(post_json)

        print(obj_json)

        add_post()

    print("\n")
    print(
        "COMPUTER: You are in menu Add post...")
    print(
        "COMPUTER [Main menu -> Add post -> ]: 1 == Add question.")
    print(
        "COMPUTER [Main menu -> Add post -> ]: 2 == Add loss.")
    print(
        "COMPUTER [Main menu -> Add post -> ]: 0 == Step back.")

    post_type = ""

    try:
        user_answer = raw_input("USER [.. -> Add post -> ]: ")

        if user_answer == "1":
            post_type = "questions"
        else:
            if user_answer == "2":
                post_type = "loss"
            else:
                if user_answer == "0":
                    main_menu()
                else:
                    print(
                        "COMPUTER [Main menu -> Add post] Unknown command. " +
                        "Retry query...")
                    add_post()

    except Exception as var_except:
        print(
            "COMPUTER [Main menu -> Add post]: Error, " + str(var_except) +
            ". Return to menu Add post...")
        add_post()

    obj_post = Post()

    if post_type == "questions":
        obj_post.set_var_number_category(1)
    else:
        if post_type == "loss":
            obj_post.set_var_number_category(2)
        else:
            print(
                "COMPUTER [Main menu -> Add post -> " +
                post_type + "]: Error, unknown category of post" +
                ". Return to Main menu...")
            main_menu()

    old_json = read_json(post_type)

    obj_post = set_number_post(post_type, obj_post, old_json)
    obj_post = set_body(post_type, obj_post)
    obj_post = set_photo(post_type, obj_post)
    obj_post = set_author(post_type, obj_post)

    write_post(post_type, obj_post, old_json)


def lists_menu():
    print(
        "COMPUTER [Main menu -> Lists menu]: Here is empty. " +
        "Return to Main menu...")
    main_menu()


def file_manager():
    print(
        "COMPUTER [Main menu -> File manager]: Here is empty. " +
        "Return to Main menu...")
    main_menu()


starter()
