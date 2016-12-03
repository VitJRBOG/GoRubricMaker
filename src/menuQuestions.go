package main

import (
	"fmt"
	"io/ioutil"
	"encoding/json"
	"strconv"
	"bufio"
	"os"
)

type question struct {
	ID int `json:"id"`
	Number string `json:"number"`
	Body string `json:"body"`
	Content []string `json:"content"`
	Author string `json:"author"`
}

func ShowMenuQuestions() {
	fmt.Println("COMPUTER: You are in MENU QUESTIONS. Here is empty. Return to main menu...")
	ShowMainMenu()
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
					fmt.Println("[.. -> Writing question -> ..] " +
						"Writing was successfully completed. Return to main menu...")
					ShowMainMenu()
				}
			}
		} else {
			if varIntUserAnswer == 0 {
				fmt.Println("[.. -> New question -> ..] Cancel of writing. Return to main menu...")
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
			arrayStringQuestionContent = collectContent(arrayStringQuestionContent, 0, varIntUserAnswer)
			return arrayStringQuestionContent
		} else {
			return arrayStringQuestionContent
		}
	}
}

func collectContent(arrayStringQuestionContent []string,
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
			return collectContent(
				arrayStringQuestionContent, varIntNumberIteration, varIntNumberContent)
		} else {
			arrayStringQuestionContent = append(arrayStringQuestionContent,
				"- " + varStringQuestionContent)
			return collectContent(
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
				//TODO //cutting link
				varStringQuestionAuthor = "*" + varStringQuestionAuthor
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