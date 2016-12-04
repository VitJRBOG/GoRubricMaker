package main

import (
	"fmt"
	"io/ioutil"
	"encoding/json"
	"strconv"
	"bufio"
	"os"
	"strings"
)

type question struct {
	ID int `json:"id"`
	Number string `json:"number"`
	Body string `json:"body"`
	Content []string `json:"content"`
	Author string `json:"author"`
}

func ShowMenuQuestions() {
	fmt.Println("COMPUTER: You are in MENU QUESTIONS.")
	fmt.Println("COMPUTER: [.. -> Lists -> Menu questions -> ..] 1 == Show questions list.")
	fmt.Println("COMPUTER: [Lists <- Menu questions] 0 == Step back, to lists.")

	var varIntUserAnswer int

	fmt.Print("USER: [.. -> Menu questions -> ] ")
	_, err := fmt.Scanf("%d", &varIntUserAnswer)
	fmt.Println("COMPUTER: ...")

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Return to menu questions...")
		ShowMenuQuestions()
	} else {
		if varIntUserAnswer == 1 {
			showListQuestions()
		} else {
			if varIntUserAnswer == 0 {
				ShowLists()
			} else {
				fmt.Println("COMPUTER: Unknown command. Return to menu questions...")
				ShowMenuQuestions()
			}
		}
	}

	ShowMainMenu()
}

func showListQuestions() {
	fmt.Println("COMPUTER: You are in LIST QUESTIONS.")

	fmt.Println("COMPUTER: [.. -> Menu questions -> List questions -> ..] 1 == " +
		"Output list of questions to console.")
	fmt.Println("COMPUTER: [.. -> Menu questions -> List questions -> ..] 2 == Export list of questions to file.")
	fmt.Println("COMPUTER: [Menu questions <- List questions] 0 == Step back, to menu questions.")

	var varIntUserAnswer int

	fmt.Print("USER: [.. -> List questions -> ] ")
	_, err := fmt.Scanf("%d", &varIntUserAnswer)

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Return to list questions...")
		ShowLists()
	} else {
		if varIntUserAnswer == 1 {
			outputQuestionsToConsole()
		} else {
			if varIntUserAnswer == 2 {
				exportQuestionsInFile()
			} else {
				if varIntUserAnswer == 0 {
					ShowMenuQuestions()
				} else {
					fmt.Println("COMPUTER: Unknown command. Return to list questions...")
					showListQuestions()
				}
			}
		}
	}
}

func outputQuestionsToConsole() {
	fmt.Println("COMPUTER: [.. -> List questions -> Output of list -> ..] Reading JSON file...")

	arrayQuestions := readJSONFileQuestions()

	fmt.Println("COMPUTER: [.. -> Output of list -> Begin of list]")
	fmt.Print("\n")
	for _, varQuestion := range arrayQuestions {
		fmt.Print(varQuestion.Number)
		fmt.Println(varQuestion.Body)
		for _, varQuestionContent := range varQuestion.Content {
			fmt.Println(varQuestionContent)
		}
		if varQuestion.Author != "" {
			fmt.Println(varQuestion.Author)
		}
		fmt.Print("\n")
	}

	fmt.Println("COMPUTER: [.. -> Output of list -> End of list] Press Enter for continue...")

	var varStringUserAnswer string

	fmt.Print("USER: [.. -> Output of list -> End of list -> ] ")
	fmt.Scanf("%s", &varStringUserAnswer)

	showListQuestions()
}

func exportQuestionsInFile() {
	fmt.Println("COMPUTER: [.. -> List questions -> Export of list] Reading JSON file...")

	arrayQuestions := readJSONFileQuestions()

	var varStringListForExport string = ""

	fmt.Println("COMPUTER: [.. -> List questions -> Export of list] Export of list to file 'questions.txt'...")
	for _, varQuestion := range arrayQuestions {
		varStringListForExport += varQuestion.Number
		varStringListForExport += varQuestion.Body + "\n"
		for _, varQuestionContent := range varQuestion.Content {
			varStringListForExport += varQuestionContent + "\n"
		}
		if varQuestion.Author != "" {
			varStringListForExport += varQuestion.Author + "\n"
		}
		varStringListForExport += "\n"
	}

	varBytesListQuestions := []byte(varStringListForExport)

	_, err := os.Create("~/Desktop/questions.txt")
	//FIXME //fix path

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Return to list questions...")
		showListQuestions()
	}

	err = ioutil.WriteFile("~/Desktop/questions.txt", varBytesListQuestions, 0)

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Return to list questions...")
		showListQuestions()
	}

	fmt.Println("COMPUTER: [.. -> List questions -> Export of list] " +
		"Export was successfully completed. Press Enter for continue...")

	var varStringUserAnswer string

	fmt.Print("USER: [.. -> Export of list -> End of list -> ] ")
	fmt.Scanf("%s", &varStringUserAnswer)

	showListQuestions()
}

func AddQuestion() {
	fmt.Println("COMPUTER: You are in ADD QUESTIONS.")
	fmt.Println("COMPUTER: [Main menu -> Add question -> ..] Reading JSON file...")

	arrayQuestions := readJSONFileQuestions()

	var varIntIdLastQuestion int = 0

	for _, varQuestion := range arrayQuestions {
		varIntIdLastQuestion = varQuestion.ID
	}

	varQuestionNewNote := makeBodyNewQuestionToJSONFileQuestions(varIntIdLastQuestion+1)

	fmt.Print("COMPUTER: [.. -> Add question -> New question] ")
	fmt.Print(varQuestionNewNote.Number)
	fmt.Println(varQuestionNewNote.Body)
	fmt.Print("COMPUTER: [.. -> Add question -> New question] ")
	fmt.Println(varQuestionNewNote.Content)
	fmt.Print("COMPUTER: [.. -> Add question -> New question] ")
	fmt.Println(varQuestionNewNote.Author)

	writeToJSONFileQuestions(arrayQuestions, varQuestionNewNote)

	fmt.Println("COMPUTER: [Main menu -> Add question -> ..] Return to main menu...")
	ShowMainMenu()
}

func readJSONFileQuestions() []question {
	varStringJSON, err := ioutil.ReadFile("./src/output/questions.json")

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Return to main menu...")
		ShowMainMenu()
	}
	var arrayQuestions []question

	json.Unmarshal(varStringJSON, &arrayQuestions)

	return arrayQuestions
}

func writeToJSONFileQuestions(arrayQuestions []question, varQuestionNewNote question) {
	fmt.Println("COMPUTER: [.. -> Add question -> New question -> ..] Write question to file? (1/0)")

	var varIntUserAnswer int

	fmt.Print("USER: [.. -> New question -> ] ")
	_, err := fmt.Scanf("%d", &varIntUserAnswer)

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Retry of query...")
		writeToJSONFileQuestions(arrayQuestions, varQuestionNewNote)
	} else {
		if varIntUserAnswer == 1 {
			arrayQuestions = append(arrayQuestions, varQuestionNewNote)

			varBytesQuestions, err := json.Marshal(arrayQuestions)
			if err != nil {
				fmt.Println("COMPUTER: ...")
				fmt.Print("COMPUTER: Error, ")
				fmt.Print(err)
				fmt.Println(". Retry of query...")
				writeToJSONFileQuestions(arrayQuestions, varQuestionNewNote)
			} else {
				err := ioutil.WriteFile("./src/output/questions.json", varBytesQuestions, 0)

				if err != nil {
					fmt.Println("COMPUTER: ...")
					fmt.Print("COMPUTER: Error, ")
					fmt.Print(err)
					fmt.Println(". Retry of query...")
					writeToJSONFileQuestions(arrayQuestions, varQuestionNewNote)
				} else {
					fmt.Println("COMPUTER: [.. -> Writing question -> ..] " +
						"Writing was successfully completed. Return to main menu...")
					ShowMainMenu()
				}
			}
		} else {
			if varIntUserAnswer == 0 {
				fmt.Println("COMPUTER: [.. -> New question -> ..] " +
					"Cancel of writing. Return to main menu...")
				ShowMainMenu()
			} else {
				fmt.Println("COMPUTER: Unknown command. Retry of query...")
				writeToJSONFileQuestions(arrayQuestions, varQuestionNewNote)
			}
		}
	}
}

func makeBodyNewQuestionToJSONFileQuestions(varIntIdNewQuestion int) question {

	var varStringQuestionNumber string = "1." + strconv.Itoa(varIntIdNewQuestion) + ") "
	varStringQuestionBody := queryBodyForNewQuestion()
	arrayStringQuestionContent := queryContentForNewQuestion()
	varStringQuestionAuthor := queryAuthorForNewQuestion()

	var varQuestionNewNote = question{
		ID: varIntIdNewQuestion,
		Number: varStringQuestionNumber,
		Body: varStringQuestionBody,
		Content: arrayStringQuestionContent,
		Author: varStringQuestionAuthor,
	}

	return varQuestionNewNote
}

func queryBodyForNewQuestion() string {
	var varStringQuestionBody string

	fmt.Print("USER: [.. -> New question -> Body -> ] ")
	objBufferIO := bufio.NewScanner(os.Stdin)

	objBufferIO.Scan()

	err := objBufferIO.Err()
	varStringQuestionBody = objBufferIO.Text()

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Retry of query...")
		return queryBodyForNewQuestion()
	} else {
		return varStringQuestionBody
	}
}

func queryContentForNewQuestion() []string {

	var arrayStringQuestionContent []string

	var varIntUserAnswer int

	fmt.Println("COMPUTER: [.. -> New question] Are contained photos? (1-10/0)")
	fmt.Print("USER: [.. -> New question -> ] ")

	_, err := fmt.Scanf("%d", &varIntUserAnswer)

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Retry of query...")
		return queryContentForNewQuestion()
	} else {
		if varIntUserAnswer != 0  {
			arrayStringQuestionContent = append(arrayStringQuestionContent, "Фото: ")
			arrayStringQuestionContent = collectContentQuestion(arrayStringQuestionContent, 0, varIntUserAnswer)
			return arrayStringQuestionContent
		} else {
			return arrayStringQuestionContent
		}
	}
}

func collectContentQuestion(arrayStringQuestionContent []string,
	varIntNumberIteration int, varIntNumberContent int) []string {

	if varIntNumberIteration != varIntNumberContent {

		var varStringQuestionContent string

		fmt.Print("USER: [.. -> New question -> Content -> ] ")
		objBufferIO := bufio.NewScanner(os.Stdin)

		objBufferIO.Scan()

		err := objBufferIO.Err()
		varStringQuestionContent = objBufferIO.Text()

		if err != nil {
			fmt.Println("COMPUTER: ...")
			fmt.Print("COMPUTER: Error, ")
			fmt.Print(err)
			fmt.Println(". Retry of query...")
			return collectContentQuestion(
				arrayStringQuestionContent, varIntNumberIteration, varIntNumberContent)
		} else {
			arrayStringQuestionContent = append(arrayStringQuestionContent,
				"- " + varStringQuestionContent)
			return collectContentQuestion(
				arrayStringQuestionContent, varIntNumberIteration + 1, varIntNumberContent)
		}
	} else {
		return arrayStringQuestionContent
	}
}

func queryAuthorForNewQuestion() string {
	var varStringQuestionAuthor string = ""

	var varIntUserAnswer int

	fmt.Println("COMPUTER: [.. -> New question] Are contained author? (1/0)")
	fmt.Print("USER: [.. -> New question -> ] ")

	_, err := fmt.Scanf("%d", &varIntUserAnswer)

	if err != nil {
		fmt.Println("COMPUTER: ...")
		fmt.Print("COMPUTER: Error, ")
		fmt.Print(err)
		fmt.Println(". Retry of query...")
		return queryAuthorForNewQuestion()
	} else {
		if varIntUserAnswer == 1 {
			fmt.Print("USER: [.. -> New question -> Author -> ] ")
			objBufferIO := bufio.NewScanner(os.Stdin)

			objBufferIO.Scan()

			err := objBufferIO.Err()
			varStringQuestionAuthor = objBufferIO.Text()

			if err != nil {
				fmt.Println("...")
				fmt.Print("COMPUTER: Error, ")
				fmt.Print(err)
				fmt.Println(". Retry of query...")
				return queryAuthorForNewQuestion()
			} else {
				varStringQuestionAuthor = strings.Replace(
					varStringQuestionAuthor, "https://vk.com/", "*", 1)
				return varStringQuestionAuthor
			}
		} else {
			if varIntUserAnswer == 0 {
				return varStringQuestionAuthor
			} else {
				fmt.Println("COMPUTER: Unknown command. Retry of query...")
				return queryAuthorForNewQuestion()
			}
		}
	}
}