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
        user_answer = raw_input("USER: [Main menu -> ]")
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


def add_post():
    print(
        "COMPUTER [Main menu -> Add post]: Here is empty. " +
        "Return to Main menu...")
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


def add_post_set_body(post_type):

    print(
        "COMPUTER [Main menu -> Add post -> " + post_type +
        " -> Body]: Copy text for post and press \"Enter\".")

    raw_input("USER [.. -> " + post_type + " -> Body]: ")

    main_menu()


def add_post_set_photo(post_type):

    print(
        "COMPUTER [Main menu -> Add post -> " + post_type +
        " -> Photo]: Copy text for post and press \"Enter\".")

    raw_input("USER [.. -> " + post_type + " -> Photo]: ")

    main_menu()


def add_post_set_author(post_type):

    print(
        "COMPUTER [Main menu -> Add post -> " + post_type +
        " -> Author]: Copy text for post and press \"Enter\".")

    raw_input("USER [.. -> " + post_type + " -> Author]: ")

    main_menu()


starter()
