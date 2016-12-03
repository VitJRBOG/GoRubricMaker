package main

import "fmt"

func ShowMainMenu() {

	fmt.Println("COMPUTER: You are in MAIN MENU.")
	fmt.Println("COMPUTER: [Main menu -> ..] 1 == Add question.")
	fmt.Println("COMPUTER: [Main menu -> ..] 2 == Add loss.")
	fmt.Println("COMPUTER: [Main menu -> ..] 3 == Show lists.")
	fmt.Println("COMPUTER: [Main menu -> ..] 0 == Close program.")

	var varIntUserAnswer int

	fmt.Print("USER: Main menu -> ")
	_, err := fmt.Scanf("%d", &varIntUserAnswer)
	fmt.Println("COMPUTER: ...")

	if err != nil {
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

}