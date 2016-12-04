package main

import (
	"bufio"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

type loss struct {
	ID      int      `json:"id"`
	Number  string   `json:"number"`
	Body    string   `json:"body"`
	Content []string `json:"content"`
	Author  string   `json:"author"`
}

func ShowMenuLoss() {
	fmt.Println("COMPUTER: You are in MENU LOSS.")
	fmt.Println("COMPUTER: [.. -> Lists -> Menu loss -> ..] 1 == Show loss list.")
	fmt.Println("COMPUTER: [Lists <- Menu loss] 0 == Step back, to lists.")

	var varIntUserAnswer int

	fmt.Print("USER: [.. -> Menu loss -> ] ")
	_, err := fmt.Scanf("%d", &varIntUserAnswer)
	fmt.Println("COMPUTER: ...")

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Return to menu loss...")
		ShowMenuLoss()
	} else {
		if varIntUserAnswer == 1 {
			showListLoss()
		} else {
			if varIntUserAnswer == 0 {
				ShowLists()
			} else {
				fmt.Println("COMPUTER: Unknown command. Return to menu loss...")
				ShowMenuLoss()
			}
		}
	}

	ShowMainMenu()
}

func showListLoss() {
	fmt.Println("COMPUTER: You are in LIST LOSS.")

	fmt.Println("COMPUTER: [.. -> Menu loss -> List loss -> ..] 1 == " +
		"Output list of loss to console.")
	fmt.Println("COMPUTER: [.. -> Menu loss -> List loss -> ..] 2 == Export list of loss to file.")
	fmt.Println("COMPUTER: [.. -> Menu loss -> List loss -> ..] 3 == Clear JSON file.")
	fmt.Println("COMPUTER: [Menu loss <- List loss] 0 == Step back, to menu loss.")

	var varIntUserAnswer int

	fmt.Print("USER: [.. -> List loss -> ] ")
	_, err := fmt.Scanf("%d", &varIntUserAnswer)

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Return to list loss...")
		ShowLists()
	} else {
		if varIntUserAnswer == 1 {
			outputLossToConsole()
		} else {
			if varIntUserAnswer == 2 {
				exportLossInFile()
			} else {
				if varIntUserAnswer == 3 {
					clearJSONFileLoss()
				} else {
					if varIntUserAnswer == 0 {
						ShowMenuLoss()
					} else {
						fmt.Println("COMPUTER: Unknown command. Return to list loss...")
						showListLoss()
					}
				}
			}
		}
	}
}

func outputLossToConsole() {
	fmt.Println("COMPUTER: [.. -> List loss -> Output of list -> ..] Reading JSON file...")

	arrayLoss := readJSONFileLoss()

	fmt.Println("COMPUTER: [.. -> Output of list -> Begin of list]")
	fmt.Print("\n")
	for _, varLoss := range arrayLoss {
		fmt.Print(varLoss.Number)
		fmt.Println(varLoss.Body)
		for _, varLossContent := range varLoss.Content {
			fmt.Println(varLossContent)
		}
		if varLoss.Author != "" {
			fmt.Println(varLoss.Author)
		}
		fmt.Print("\n")
	}

	fmt.Println("COMPUTER: [.. -> Output of list -> End of list] Press Enter for continue...")

	var varStringUserAnswer string

	fmt.Print("USER: [.. -> Output of list -> End of list -> ] ")
	fmt.Scanf("%s", &varStringUserAnswer)

	showListLoss()
}

func exportLossInFile() {
	fmt.Println("COMPUTER: [.. -> List loss -> Export of list] Reading JSON file...")

	arrayLoss := readJSONFileLoss()

	var varStringListForExport string = ""

	fmt.Println("COMPUTER: [.. -> List loss -> Export of list] Export of list to file 'loss.txt'...")
	for _, varLoss := range arrayLoss {
		varStringListForExport += varLoss.Number
		varStringListForExport += varLoss.Body + "\n"
		for _, varLossContent := range varLoss.Content {
			varStringListForExport += varLossContent + "\n"
		}
		if varLoss.Author != "" {
			varStringListForExport += varLoss.Author + "\n"
		}
		varStringListForExport += "\n"
	}

	varBytesListLoss := []byte(varStringListForExport)

	_, err := os.Create("./src/output/loss.txt")

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Return to list loss...")
		showListLoss()
	}

	err = ioutil.WriteFile("./src/output/loss.txt", varBytesListLoss, 0)

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Return to list loss...")
		showListLoss()
	}

	fmt.Println("COMPUTER: [.. -> List loss -> Export of list] " +
		"Export was successfully completed. Press Enter for continue...")

	var varStringUserAnswer string

	fmt.Print("USER: [.. -> Export of list -> End of list -> ] ")
	fmt.Scanf("%s", &varStringUserAnswer)

	showListLoss()
}

func clearJSONFileLoss() {
	fmt.Println("COMPUTER: [.. -> List loss -> Cleaning JSON file] " +
		"Cleaning JSON file 'loss.json'. Are you sure? (1/0)")

	var varIntUserAnswer int

	fmt.Print("USER: [.. -> Cleaning JSON file -> ] ")
	_, err := fmt.Scanf("%d", &varIntUserAnswer)

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Retry of query...")
		clearJSONFileLoss()
	} else {
		if varIntUserAnswer == 1 {
			ioutil.WriteFile("./src/json/loss.json", nil, 0)
			fmt.Println("COMPUTER: [.. -> List loss -> Cleaning JSON file] Cleaning " +
				"successfully completed. Return to list loss.")
		} else {
			if varIntUserAnswer == 0 {
				fmt.Println("COMPUTER: [.. -> List loss -> Cleaning JSON file] Cancel. " +
					"Return to list loss...")
				showListLoss()
			} else {
				fmt.Println("COMPUTER: Unknown command. Retry of query...")
				clearJSONFileLoss()
			}
		}
	}

	showListLoss()
}

func AddLoss() {
	fmt.Println("COMPUTER: You are in ADD LOSS.")
	fmt.Println("COMPUTER: [Main menu -> Add loss -> ..] Reading JSON file...")

	arrayLoss := readJSONFileLoss()

	var varIntIdLastLoss int = 0

	for _, varLoss := range arrayLoss {
		varIntIdLastLoss = varLoss.ID
	}

	varLossNewNote := makeBodyNewLossToJSONFileLoss(varIntIdLastLoss + 1)

	fmt.Print("COMPUTER: [.. -> Add loss -> New loss] ")
	fmt.Print(varLossNewNote.Number)
	fmt.Println(varLossNewNote.Body)
	fmt.Print("COMPUTER: [.. -> Add loss -> New loss] ")
	fmt.Println(varLossNewNote.Content)
	fmt.Print("COMPUTER: [.. -> Add loss -> New loss] ")
	fmt.Println(varLossNewNote.Author)

	writeToJSONFileLoss(arrayLoss, varLossNewNote)

	fmt.Println("COMPUTER: [Main menu -> Add loss -> ..] Return to main menu...")
	ShowMainMenu()
}

func readJSONFileLoss() []loss {
	varStringJSON, err := ioutil.ReadFile("./src/json/loss.json")

	if err != nil {
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Return to main menu...")
		ShowMainMenu()
	}
	var arrayLoss []loss

	json.Unmarshal(varStringJSON, &arrayLoss)

	return arrayLoss
}

func writeToJSONFileLoss(arrayLoss []loss, varLossNewNote loss) {
	fmt.Println("COMPUTER: [.. -> Add loss -> New loss -> ..] Write loss to file? (1/0)")

	var varIntUserAnswer int

	fmt.Print("USER: [.. -> New loss -> ] ")
	_, err := fmt.Scanf("%d", &varIntUserAnswer)

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Retry of query...")
		writeToJSONFileLoss(arrayLoss, varLossNewNote)
	} else {
		if varIntUserAnswer == 1 {
			arrayLoss = append(arrayLoss, varLossNewNote)

			varBytesLoss, err := json.Marshal(arrayLoss)
			if err != nil {
				fmt.Println("COMPUTER: ...")
				fmt.Print("COMPUTER: Error, ")
				fmt.Print(err)
				fmt.Println(". Retry of query...")
				writeToJSONFileLoss(arrayLoss, varLossNewNote)
			} else {
				err := ioutil.WriteFile("./src/json/loss.json", varBytesLoss, 0)

				if err != nil {
					fmt.Println("COMPUTER: ...")
					fmt.Print("COMPUTER: Error, ")
					fmt.Print(err)
					fmt.Println(". Retry of query...")
					writeToJSONFileLoss(arrayLoss, varLossNewNote)
				} else {
					fmt.Println("COMPUTER: [.. -> Writing loss -> ..] " +
						"Writing was successfully completed. Return to main menu...")
					ShowMainMenu()
				}
			}
		} else {
			if varIntUserAnswer == 0 {
				fmt.Println("COMPUTER: [.. -> New loss -> ..] " +
					"Cancel of writing. Return to main menu...")
				ShowMainMenu()
			} else {
				fmt.Println("COMPUTER: Unknown command. Retry of query...")
				writeToJSONFileLoss(arrayLoss, varLossNewNote)
			}
		}
	}
}

func makeBodyNewLossToJSONFileLoss(varIntIdNewLoss int) loss {

	var varStringLossNumber string = "2." + strconv.Itoa(varIntIdNewLoss) + ") "
	varStringLossBody := queryBodyForNewLoss()
	arrayStringLossContent := queryContentForNewLoss()
	varStringLossAuthor := queryAuthorForNewLoss()

	var varLossNewNote = loss{
		ID:      varIntIdNewLoss,
		Number:  varStringLossNumber,
		Body:    varStringLossBody,
		Content: arrayStringLossContent,
		Author:  varStringLossAuthor,
	}

	return varLossNewNote
}

func queryBodyForNewLoss() string {
	var varStringLossBody string

	fmt.Print("USER: [.. -> New loss -> Body -> ] ")
	objBufferIO := bufio.NewScanner(os.Stdin)

	objBufferIO.Scan()

	err := objBufferIO.Err()
	varStringLossBody = objBufferIO.Text()

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Retry of query...")
		return queryBodyForNewLoss()
	} else {
		return varStringLossBody
	}
}

func queryContentForNewLoss() []string {

	var arrayStringLossContent []string

	var varIntUserAnswer int

	fmt.Println("COMPUTER: [.. -> New loss] Are contained photos? (1-10/0)")
	fmt.Print("USER: [.. -> New loss -> ] ")

	_, err := fmt.Scanf("%d", &varIntUserAnswer)

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Retry of query...")
		return queryContentForNewLoss()
	} else {
		if varIntUserAnswer != 0 {
			arrayStringLossContent = append(arrayStringLossContent, "Фото: ")
			arrayStringLossContent = collectContentLoss(arrayStringLossContent, 0, varIntUserAnswer)
			return arrayStringLossContent
		} else {
			return arrayStringLossContent
		}
	}
}

func collectContentLoss(arrayStringLossContent []string,
	varIntNumberIteration int, varIntNumberContent int) []string {

	if varIntNumberIteration != varIntNumberContent {

		var varStringLossContent string

		fmt.Print("USER: [.. -> New loss -> Content -> ] ")
		objBufferIO := bufio.NewScanner(os.Stdin)

		objBufferIO.Scan()

		err := objBufferIO.Err()
		varStringLossContent = objBufferIO.Text()

		if err != nil {
			fmt.Println("COMPUTER: ...")
			fmt.Print("COMPUTER: Error, ")
			fmt.Print(err)
			fmt.Println(". Retry of query...")
			return collectContentLoss(
				arrayStringLossContent, varIntNumberIteration, varIntNumberContent)
		} else {
			arrayStringLossContent = append(arrayStringLossContent,
				"- "+varStringLossContent)
			return collectContentLoss(
				arrayStringLossContent, varIntNumberIteration+1, varIntNumberContent)
		}
	} else {
		return arrayStringLossContent
	}
}

func queryAuthorForNewLoss() string {
	var varStringLossAuthor string = ""

	var varIntUserAnswer int

	fmt.Println("COMPUTER: [.. -> New loss] Are contained author? (1/0)")
	fmt.Print("USER: [.. -> New loss -> ] ")

	_, err := fmt.Scanf("%d", &varIntUserAnswer)

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Retry of query...")
		return queryAuthorForNewLoss()
	} else {
		if varIntUserAnswer == 1 {
			fmt.Print("USER: [.. -> New loss -> Author -> ] ")
			objBufferIO := bufio.NewScanner(os.Stdin)

			objBufferIO.Scan()

			err := objBufferIO.Err()
			varStringLossAuthor = objBufferIO.Text()

			if err != nil {
				fmt.Println("...")
				fmt.Print("COMPUTER: Error, ")
				fmt.Print(err)
				fmt.Println(". Retry of query...")
				return queryAuthorForNewLoss()
			} else {
				varStringLossAuthor = strings.Replace(
					varStringLossAuthor, "https://vk.com/", "*", 1)
				return varStringLossAuthor
			}
		} else {
			if varIntUserAnswer == 0 {
				return varStringLossAuthor
			} else {
				fmt.Println("COMPUTER: Unknown command. Retry of query...")
				return queryAuthorForNewLoss()
			}
		}
	}
}
