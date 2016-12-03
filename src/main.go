package main

import (
	"os"
	"fmt"
)

func main() {
	ShowMainMenu()
}

func Exit(varIntErrorId int) {
	fmt.Println("COMPUTER: Exit from program...")
	os.Exit(varIntErrorId)
}