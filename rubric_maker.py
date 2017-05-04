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
        "COMPUTER [Main menu]: You are in Main menu...")
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
            var_author_url = self.var_author_url[self.var_author_url.rfind('/') + 1:]
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
        old_json = json.loads(open("json/" + post_type + ".json", 'r').read())  # dict

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

            if len(old_json) >= 1:
                obj_post.set_var_number_post(len(old_json) + 1)
            else:
                obj_post.set_var_number_post(1)

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
                "COMPUTER [.. -> Add " + post_type +
                " -> Body]: Copy text for post and press \"Enter\", or enter \"00\" for cancel.")

            user_answer = raw_input("USER [.. -> Add " + post_type + " -> Body]: ")

            if user_answer == "00":
                print("COMPUTER: Cancel...")
                add_post()
            else:
                cb = gtk.clipboard_get()

                text = str(gtk.Clipboard.wait_for_text(cb))

                obj_post.set_var_body(text)

                print(
                    "\n" +
                    str(obj_post.get_var_number_category()) + "." +
                    str(obj_post.get_var_number_post()) + ") " +
                    obj_post.get_var_body() + str() + "\n")

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
                "COMPUTER [.. -> Add " + post_type +
                " -> Photo]: Enter count photo and press \"Enter\", or enter \"00\" for cancel.")

            list_photo = ""

            user_answer = raw_input("USER [.. -> Add " + post_type + " -> Photo]: ")

            if user_answer == "00":
                print("COMPUTER: Cancelling...")
                main_menu()
            else:
                if user_answer == "0":
                    return obj_post
                else:
                    if int(user_answer) > 0 and int(user_answer) <= 10:
                        photo_count = int(user_answer)
                        list_photo = []
                        list_photo.append("Фото:")

                        cb = gtk.clipboard_get()

                        i = 1

                        while i <= photo_count:
                            print(
                                "COMPUTER [.. -> Add " + post_type +
                                " -> Photo №" + str(i) + "]: Copy URL and press \"Enter\".")

                            raw_input("USER [.. -> Add photo -> Photo №" + str(i) + "]: ")

                            url = str(gtk.Clipboard.wait_for_text(cb))
                            url = url.decode("utf8")

                            print(
                                "COMPUTER [.. -> Add " + post_type +
                                " -> Photo -> №" + str(i) + "]: " + str(url))

                            list_photo.append("- " + str(url))

                            if i == photo_count:
                                break

                            i += 1

                        obj_post.set_list_photo(list_photo)
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
            "COMPUTER [.. -> Add " + post_type +
            " -> Author]: Enter \"1\", if need signature, or \"0\", if not. " +
            "Enter \"00\" for cancel.")

        user_answer = raw_input("USER [.. -> Add " + post_type + " -> Author]: ")

        if user_answer == "00":
            print("COMPUTER: Cancel...")
            add_post()
        else:
            if user_answer == "0":
                return obj_post
            else:
                if user_answer == "1":
                    try:
                        cb = gtk.clipboard_get()

                        print(
                            "COMPUTER [.. -> Add " + post_type +
                            " -> Author -> URL] Copy link to author's page and press \"Enter\". " +
                            "Enter \"00\" for cancel.")

                        user_answer = raw_input("USER [.. -> Author -> URL] ")

                        url = ""

                        if user_answer == "00":
                            add_post()
                        else:
                            url = str(gtk.Clipboard.wait_for_text(cb))
                            url = url.decode("utf8")

                            print(
                                "COMPUTER [.. -> Add " + post_type +
                                "-> Author -> URL] " + url)
                            obj_post.set_var_author_url(url)

                            print(
                                "COMPUTER [.. -> Add " + post_type +
                                "-> Author -> Full name] " +
                                "Copy author's full name and press \"Enter\". " +
                                "Enter \"00\" for cancel.")

                            user_answer = raw_input("USER [.. -> Author -> Full name] ")

                            name = ""

                            if user_answer == "00":
                                print("COMPUTER: Cancel...")
                                add_post()
                            else:
                                name = str(gtk.Clipboard.wait_for_text(cb))
                                name = name.decode("utf8")

                                print(
                                    "COMPUTER [Main menu -> Add post -> Add " + post_type +
                                    " -> Author -> Full name] " + name)
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
            "COMPUTER [.. -> Add " + post_type +
            " -> Author]: Copy text for post and press \"Enter\".")

        add_post()

    def write_post(post_type, obj_post, old_json):

        print(
            "\n" +
            "COMPUTER [.. -> Add post -> Write post]: " +
            "Write this to file \"questions.json\"? (1/0)")

        user_answer = raw_input("USER [.. -> Add post -> Write post]: ")

        if user_answer == '0':
            print(
                "COMPUTER [Main menu -> Add post -> Write post]: " +
                "Cancel of writing. Return to menu Add post...")
        else:
            if user_answer == '1':
                try:

                    post_json = {
                        "category": obj_post.get_var_number_category(),
                        "num": obj_post.get_var_number_post(),
                        "body": obj_post.get_var_body(),
                        "photo": obj_post.get_list_photo(),
                        "author": obj_post.get_var_author()}

                    done_json = old_json

                    done_json[int(post_json.get("num") - 1)] = post_json

                    json_file = open("json/" + post_type + ".json", 'w')

                    json_file.write(json.dumps(done_json, indent=4, ensure_ascii=False))

                    json_file.close()

                    print(
                        "COMPUTER [Main menu -> Add post -> Add " + post_type +
                        " -> Write post] Post was successfully written. Return to menu Add Post.")

                except Exception as var_except:
                    print(
                        "COMPUTER [Main menu -> Add post -> Add " + post_type +
                        " -> Write post]: Error, " + str(var_except) +
                        ". Return to menu Add post...")
            else:
                print(
                    "COMPUTER [Main menu -> Add post -> Write post]" +
                    " Unknown command. Retry query...")
                return write_post(post_type, obj_post, old_json)

        add_post()

    print(
        "\n" +
        "COMPUTER [Main menu -> Add post]: You are in menu Add post...")
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
            ". Return to Main menu...")
        main_menu()

    obj_post = Post()

    if post_type == "questions":
        obj_post.set_var_number_category(1)
    else:
        if post_type == "loss":
            obj_post.set_var_number_category(2)
        else:
            print(
                "COMPUTER [Main menu -> Add post -> Add " +
                post_type + "]: Error, unknown category of post" +
                ". Return to Main menu...")
            main_menu()

    old_json = read_json(post_type)

    obj_post = set_number_post(post_type, obj_post, old_json)
    obj_post = set_body(post_type, obj_post)
    obj_post = set_photo(post_type, obj_post)
    obj_post = set_author(post_type, obj_post)

    print("\n" + str(obj_post.get_var_number_category()) + "." +
          str(obj_post.get_var_number_post()) + ") " +
          str(obj_post.get_var_body()))

    if obj_post.get_list_photo() != "":
        list_photo = obj_post.get_list_photo()
        for i, var_photo in enumerate(list_photo):
            print(var_photo)

    if str(obj_post.get_var_author()) != "":
        print(obj_post.get_var_author())

    write_post(post_type, obj_post, old_json)

    main_menu()


def lists_menu():

    def show_list(old_json):
        for i, nothing in enumerate(old_json):
            print(
                "\n" +
                str(old_json[str(i)]["category"]) +
                "." + str(old_json[str(i)]["num"]) +
                ") " + str(old_json[str(i)]["body"]))
            if old_json[str(i)]["photo"] != "":
                for j, nothing in enumerate(old_json[str(i)]["photo"]):
                    print(str(old_json[str(i)]["photo"][j]))
            if old_json[str(i)]["author"] != "":
                print(str(old_json[str(i)]["author"]))
        lists_menu()

    print(
        "\n" +
        "COMPUTER [Main menu -> Lists menu]: You are in Lists menu.")
    print(
        "COMPUTER [Main menu -> Lists menu -> ]: 1 == Show list of questions.")
    print(
        "COMPUTER [Main menu -> Lists menu -> ]: 2 == Show list of loss.")
    print(
        "COMPUTER [Main menu -> Lists menu -> ]: 0 == Step back.")

    try:
        user_answer = raw_input("USER: [.. -> Lists menu -> ] ")
        if user_answer == "0":
            main_menu()
        else:
            if user_answer == "1":
                old_json = read_json("questions")
                show_list(old_json)
            else:
                if user_answer == "2":
                    old_json = read_json("loss")
                    show_list(old_json)
                else:
                    print(
                        "COMPUTER [Main menu -> Lists menu] Unknown command. " +
                        "Retry query...")
                    lists_menu()
    except Exception as var_except:
        print(
            "COMPUTER [Main menu -> Lists menu]: Error, " + str(var_except) +
            ". Return to Main menu...")
        main_menu()

    main_menu()


def file_manager():

    def menu_clear_files():

        def algorythm_clear_file(post_type):
            print(
                "COMPUTER [.. -> Clear files -> Clear " +
                post_type + "]: Are you sure? (1/0)")

            try:
                user_answer = raw_input("USER: [.. -> Clear files -> Clear " +
                                        post_type + "] ")
                if user_answer == "0":
                    menu_clear_files()
                else:
                    if user_answer == "1":
                        file = open("json/" + post_type + ".json", 'w')
                        file.write("{}")
                        file.close()
                        print(
                            "COMPUTER [Main menu -> File manager -> Clear files -> " +
                            "Clear " + post_type + "] File was successfully cleaned. " +
                            "Return to menu Clear files.")
                        menu_clear_files()
                    else:
                        print(
                            "COMPUTER [Main menu -> Clear files -> Clear " + post_type + "] " +
                            "Unknown command. Retry query...")
                        export_files()
            except Exception as var_except:
                print(
                    "COMPUTER [Main menu -> Clear files -> Clear " + post_type +
                    "]: Error, " + str(var_except) + ". Return to Main menu...")
                main_menu()

        print(
            "\n" +
            "COMPUTER [.. -> File manager -> Clear files]: " +
            "You are in menu Clear files.")
        print(
            "COMPUTER [.. -> File manager -> Clear files -> ]: " +
            "1 == Clear \"questions.json\".")
        print(
            "COMPUTER [.. -> File manager -> Clear files -> ]: " +
            "2 == Clear \"loss.json\".")
        print(
            "COMPUTER [.. -> File manager -> Clear files -> ]: 0 == Step back.")

        try:
            user_answer = raw_input("USER: [.. -> File manager -> Clear files] ")
            if user_answer == "0":
                file_manager()
            else:
                if user_answer == "1":
                    algorythm_clear_file("questions")
                else:
                    if user_answer == "2":
                        algorythm_clear_file("loss")
                    else:
                        print(
                            "COMPUTER [Main menu -> File manager -> Clear files] " +
                            "Unknown command. Retry query...")
                        menu_clear_files()
        except Exception as var_except:
            print(
                "COMPUTER [Main menu -> File manager -> Clear files]: Error, " + str(var_except) +
                ". Return to Main menu...")
            main_menu()

    def export_files():

        def algorythm_export_files(old_json, post_type):
            output_row = ""

            for i, nothing in enumerate(old_json):
                if i > 0:
                    output_row = output_row + "\n"
                output_row = output_row + "\n" +\
                    str(old_json[str(i)]["category"]) +\
                    "." + str(old_json[str(i)]["num"]) +\
                    ") " + str(old_json[str(i)]["body"])
                if old_json[str(i)]["photo"] != "":
                    for j, nothing in enumerate(old_json[str(i)]["photo"]):
                        output_row = output_row + "\n" + str(old_json[str(i)]["photo"][j])
                if old_json[str(i)]["author"] != "":
                    output_row = output_row + "\n" + str(old_json[str(i)]["author"])

            file = open("output/" + post_type + ".txt", "w")
            file.write(output_row)
            file.close()

        print(
            "COMPUTER [.. -> File manager -> Export files]: Are you sure? (1/0)")

        try:
            user_answer = raw_input("USER: [.. -> File manager -> Export files] ")
            if user_answer == "0":
                file_manager()
            else:
                if user_answer == "1":
                    algorythm_export_files(read_json("questions"), "questions")
                    algorythm_export_files(read_json("loss"), "loss")
                else:
                    print(
                        "COMPUTER [Main menu -> File manager -> Export files] Unknown command. " +
                        "Retry query...")
                    export_files()
        except Exception as var_except:
            print(
                "COMPUTER [Main menu -> File manager -> Export files]: Error, " + str(var_except) +
                ". Return to Main menu...")
            main_menu()

    print(
        "\n" +
        "COMPUTER [Main menu -> File manager]: You are in File manager.")
    print(
        "COMPUTER [Main menu -> File manager -> ]: 1 == Clear files.")
    print(
        "COMPUTER [Main menu -> File manager -> ]: 2 == Export files.")
    print(
        "COMPUTER [Main menu -> File manager -> ]: 0 == Step back.")

    try:
        user_answer = raw_input("USER: [.. -> File manager -> ] ")
        if user_answer == "0":
            main_menu()
        else:
            if user_answer == "1":
                menu_clear_files()
            else:
                if user_answer == "2":
                    export_files()
                else:
                    print(
                        "COMPUTER [Main menu -> File manager] Unknown command. " +
                        "Retry query...")
                    file_manager()
    except Exception as var_except:
        print(
            "COMPUTER [Main menu -> File manager]: Error, " + str(var_except) +
            ". Return to Main menu...")
        main_menu()

    main_menu()


starter()
