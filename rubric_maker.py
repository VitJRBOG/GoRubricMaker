# coding: utf8


def starter():
    try:
        open("json/questions.json", 'w')
        open("json/loss.json", 'w')
    except Exception as var_except:
        print(
            "COMPUTER: Error, " + str(var_except) + ". Exit from program...")
        exit()
    main_menu()


def main_menu():
    print(
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
    var_author = ""

    def set_var_number_category(self, var_number_category_inner):
        self.var_number_category = var_number_category_inner

    def set_var_number_post(self, var_number_post_inner):
        self.var_number_post = var_number_post_inner

    def set_var_body(self, var_body_inner):
        self.var_body = var_body_inner

    def set_var_photo(self, var_photo_inner):
        self.var_photo = var_photo_inner

    def set_var_author(self, var_author_inner):
        self.var_author = var_author_inner

    def get_var_number_category(self):
        return self.var_number_category

    def get_var_number_post(self):
        return self.var_number_post

    def get_var_body(self):
        var_body_this = str(self.var_number_category) + "." +\
                        str(self.var_number_post) + ") " +\
                        self.var_body
        return var_body_this

    def get_var_photo(self):
        return self.var_photo

    def get_var_author(self):
        return self.var_author

    def __init__(self):
        self.var_number_category = 0
        self.var_number_post = 0
        self.var_body = ""
        self.var_photo = ""
        self.var_author = ""


def add_post():
    print(
        "COMPUTER: You are in menu Add post...")
    print(
        "COMPUTER [Main menu -> ]: 1 == Add question.")
    print(
        "COMPUTER [Main menu -> ]: 2 == Add loss.")
    print(
        "COMPUTER [Main menu -> ]: 0 == Step back.")

    post_type = ""

    try:
        user_answer = raw_input("USER [.. -> Add post ->]: ")

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
                        "COMPUTER [Main menu] Unknown command. " +
                        "Retry query...")
                    add_post()
    except Exception as var_except:
        print(
            "COMPUTER [Main menu]: Error, " + str(var_except) +
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
                "COMPUTER [Main menu -> Add post -> " +
                post_type + "]: Error, unknown category of post" +
                ". Return to Main menu...")
            main_menu()

    obj_post.set_var_body(add_post_set_body(post_type, obj_post))
    obj_post.set_var_photo(add_post_set_photo(post_type, obj_post))
    obj_post.set_var_author(add_post_set_author(post_type, obj_post))

    main_menu()


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


def add_post_make_post():
    print(
        "COMPUTER [Main menu -> Add post -> Body]: Here is empty.  " +
        "Return to Main menu...")
    main_menu()


def add_post_set_number_post(post_type, obj_post):

    print(
        "COMPUTER [Main menu -> Lists menu]: Here is empty. " +
        "Return to Main menu...")

    main_menu()


def add_post_set_body(post_type, obj_post):

    print(
        "COMPUTER [Main menu -> Add post -> " + post_type +
        " -> Body]: Copy text for post and press \"Enter\".")

    raw_input("USER [.. -> " + post_type + " -> Body]: ")

    main_menu()


def add_post_set_photo(post_type, obj_post):

    print(
        "COMPUTER [Main menu -> Add post -> " + post_type +
        " -> Photo]: Copy text for post and press \"Enter\".")

    raw_input("USER [.. -> " + post_type + " -> Photo]: ")

    main_menu()


def add_post_set_author(post_type, obj_post):

    print(
        "COMPUTER [Main menu -> Add post -> " + post_type +
        " -> Author]: Copy text for post and press \"Enter\".")

    raw_input("USER [.. -> " + post_type + " -> Author]: ")

    main_menu()


starter()
