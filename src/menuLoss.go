package main

import (
	"fmt"
	"io/ioutil"
	"encoding/json"
)

type loss struct {
	ID int `json:"id"`
	Body string `json:"body"`
	Content []string `json:"content"`
	Author string `json:"author"`
}

func ShowMenuLoss() {
	fmt.Println("COMPUTER: You are in MENU LOSS. Here is empty. Return to main menu...")
	ShowMainMenu()
}

func AddLoss() {
	fmt.Println("COMPUTER: You are in ADD LOSS. Here is empty. Return to main menu...")
	ShowMainMenu()
}

func readJSONFileLoss() []loss {
	varStringJSON, err := ioutil.ReadFile("./src/output/loss.json")

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