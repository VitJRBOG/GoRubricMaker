package main

import "fmt"

func ShowMainMenu() {

	fmt.Println("COMPUTER: You are in MAIN MENU.")
	fmt.Println("COMPUTER: [Main menu -> ..] 1 == Add question.")
	fmt.Println("COMPUTER: [Main menu -> ..] 2 == Add loss.")
	fmt.Println("COMPUTER: [Main menu -> ..] 3 == Show lists.")
	fmt.Println("COMPUTER: [Exit <- Main menu] 0 == Close program.")

	var varIntUserAnswer int

	fmt.Print("USER: [Main menu -> ] ")
	_, err := fmt.Scanf("%d", &varIntUserAnswer)

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Return to main menu...")
		ShowMainMenu()
	} else {
		if varIntUserAnswer == 1 {
			AddQuestion()
		} else {
			if varIntUserAnswer == 2 {
				AddLoss()
			} else {
				if varIntUserAnswer == 3 {
					ShowLists()
				} else {
					if varIntUserAnswer == 0 {
						Exit(0)
					} else {
						fmt.Println("COMPUTER: Unknown command. Return to main menu...")
						ShowMainMenu()
					}
				}
			}
		}
	}
}

func ShowLists() {
	fmt.Println("COMPUTER: You are in LISTS.")
	fmt.Println("COMPUTER: [Main menu -> Lists -> ..] 1 == Show menu questions.")
	fmt.Println("COMPUTER: [Main menu -> Lists -> ..] 2 == Show menu loss.")
	fmt.Println("COMPUTER: [Main menu <- Lists] 0 == Step back, to main menu.")

	var varIntUserAnswer int

	fmt.Print("USER: [.. -> Lists -> ] ")
	_, err := fmt.Scanf("%d", &varIntUserAnswer)

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Return to lists...")
		ShowLists()
	} else {
		if varIntUserAnswer == 1 {
			ShowMenuQuestions()
		} else {
			if varIntUserAnswer == 2 {
				ShowMenuLoss()
			} else {
				if varIntUserAnswer == 0 {
					ShowMainMenu()
				} else {
					fmt.Println("COMPUTER: Unknown command. Return to lists...")
					ShowLists()
				}
			}
		}
	}
}