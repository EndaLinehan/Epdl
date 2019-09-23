/**************************************************************/
/*                                                            */
/*   Program to demonstrate the implmentation of a            */
/*   linked list of books.								  */
/*                                                            */
/**************************************************************/
#define _CRT_SECURE_NO_WARNINGS
#define bool int
#define false 0
#define true (!false)

//Libraries
#include <stdio.h>
#include <stdlib.h>
#include <string.h>



//Stucture template for data part of a LinearNode
struct book {

	char bookId[10];
	char bookName[35];
	char bookAuthor[35];
	int yearOfP;
	bool status;
	char custName[35];
	int numOfLoans;

};

//Stucture template for one node
struct LinearNode {
	struct book *element;
	struct LinearNode *next;
};


// Function prototypes
void addNodes();  //adding nodes to end of the list
void deleteNode(); // delete a specific node
void viewAllNodes();
bool isEmpty();
void displayPopular();
void loanBook();
void returnBook();
void viewSingleNode();
void rentARoom();

// Global Variables
struct LinearNode *front = NULL;
struct LinearNode *last = NULL;
int count = 0;
/**************MAIN FUNCTION*******************/
int main() {
	int userChoice;

	do {
		printf("\n1. Add a new book to the library");
		printf("\n2. Allow customer to take out a book");
		printf("\n3. Allow customer to return a book");
		printf("\n4. Delete an old book from stock");
		printf("\n5. View all books");
		printf("\n6. View a specific book");
		printf("\n7. View details of most popular and least popular books");
		printf("\n8. Rent a Room");
		printf("\n9. Exit the system");

		printf("\n Please enter the number of your choice:");
		scanf("%d", &userChoice);

		switch (userChoice)
		{
		case 1:
			printf("\nYou have selected enter book");
			addNodes();//
			break;
		case 2:
			printf("\nYou have selected for a customer to loan a book");
			loanBook();//
			break;
		case 3:
			printf("\nYou have selected for a customer to return a book");
			returnBook();//
			break;
		case 4:
			printf("\nYou have selected to delete a book");
			deleteNode(); //
			break;
		case 5:
			printf("\nYou have selected to view all books");
			viewAllNodes();//
			break;
		case 6:
			printf("\nYou have selected to view a single books");
			viewSingleNode();//
			break;
		case 7:
			printf("\nView details of most popular and least popular books");
			displayPopular();
			break;
		case 8:
			//Personal feature in which a user can go onto the system and rent a room for an hour at a time
			printf("\n Rent a Room");
			rentARoom();
			break;
		case 9:
			printf("\nExit the system");
			exit(0);
			break;
		default: printf("\nError - no such menu option");
		}
	} while (true);


	getchar();
	getchar();
}



/**********ADD THREE NODES TO THE LIST******************/
// Each new node is added to the end of the list
// Add books to the linked list
void addNodes() {


	struct LinearNode *aNode;
	struct book *anElement;

	aNode = front;

	//Create space for data part of node
	anElement = (struct book *)malloc(sizeof(struct book));
	char tempBook[10];
	if (count != 10) {
		if (anElement == NULL)
			printf("\nError - no space for the new element\n");

		else
		{
			//Input value into the data part
			printf("\n Enter the book ID:");
			scanf("%s", tempBook);
			for (int i = 0; i < strlen(anElement->bookId); i++)
			{	/*
				if (isDigit(aNode->element->bookId[0]) !=0) {
					if (isDigit(aNode->element->bookId[1]) != 0) {
						if (isDigit(aNode->element->bookId[2]) != 0) {
							if (isDigit(aNode->element->bookId[3]) != 0) {
								if (anElement->bookId[4] == '-') {
									if (isDigit(aNode->element->bookId[5]) != 0) {
										if (isDigit(aNode->element->bookId[6]) != 0) {
											if (isDigit(aNode->element->bookId[7]) != 0) {
												if (isDigit(aNode->element->bookId[8]) != 0)
												{*/
				tempBook == anElement->bookId;
				/* }
			}
		}
	}
}
}
}
}
}
else {
	printf("The wrong format submitted");
}
*/
			}
			printf("\nEnter the name of the Book:");
			scanf("%[^\n]s", anElement->bookName);

			printf("\nEnter The Author of the Book:");
			scanf("%s[^\n]", anElement->bookAuthor);

			printf("\nEnter the year of Publication:");
			scanf("%d", &anElement->yearOfP);

			anElement->status == false;
			anElement->custName == NULL;
			anElement->numOfLoans = 0;

			count++;



			// create space for new node that will contain data
			aNode = (struct LinearNode *)malloc(sizeof(struct LinearNode));

			if (aNode == NULL)
				printf("Error - no space for the new node\n");
			else { // add data part to the node
				aNode->element = anElement;
				aNode->next = NULL;

				//add node to end of the list
				if (isEmpty())
				{
					front = aNode;
					last = aNode;
				}
				else {
					last->next = aNode;
					last = aNode;
				} //end else
			}//end else
		}//end else 
	}
	else
	{
		printf("There is no space for a new book");
	}
} //end addNodes

//Loan a book from the library
void loanBook()
{
	char aElement[10];
	struct LinearNode *current, *previous;
	bool notFound = true;

	printf("\n");
	if (isEmpty())
		printf("\nError - there are no books in the list\n");
	else {
		printf("Please enter the book ID you are loaning:");
		scanf("%s", &aElement);
		getchar;

		current = previous = front;

		while (notFound && current != NULL) {
			if (strcmp(current->element->bookId, aElement))
				notFound = false;
			else {
				previous = current;
				current = current->next;
			}//end else
		} //end while

		if (notFound)
			printf("\nError - there is not such book with value %s\n", aElement);
		else {
			if (current->element->status == true)
			{
				printf("Sorry this book is already loaned");
			}
			else
			{
				printf("Please enter customer name:");
				scanf("%s", &current->element->custName);
				current->element->status == true;
				current->element->numOfLoans = current->element->numOfLoans + 1;
			}
		}//end else
	}//end else
}


//Return a book
void returnBook()
{
	char aNumber[10];
	struct LinearNode *current, *previous;
	bool notFound = true;

	printf("\n");
	if (isEmpty())
		printf("\nError - there are no nodes in the list\n");
	else {
		printf("Please enter the book ID you are returning:");
		scanf("%s", &aNumber);
		getchar();

		current = previous = front;

		while (notFound && current != NULL) {
			if (strcmp(current->element->bookId, aNumber))
				notFound = false;
			else {
				previous = current;
				current = current->next;
			}//end else
		} //end while

		if (notFound)
			printf("\nError - there is not such node with value %d\n", aNumber);
		else {
			if (current->element->status == false)
			{
				printf("Sorry this book isn't currently loaned");
			}
			else
			{

				current->element->custName == NULL;
				current->element->status == false;
			}
		}//end else
	}
}


void deleteNode() {
	char aBook[10];
	struct LinearNode *current, *previous;
	bool notFound = true;

	printf("\n");
	if (isEmpty())
		printf("Error - there are no nodes in the list\n");
	else {
		printf("Please enter the book ID you are searching for:");
		scanf("%d", &aBook);
		getchar;

		current = previous = front;

		while (notFound && current != NULL) {
			if (strcmp(current->element->bookId, aBook))
				notFound = false;
			else {
				previous = current;
				current = current->next;
			}//end else
		} //end while
		count--;
		if (notFound)
			printf("Error - there is not such bookID with value %s\n", aBook);
		else {
			if (current == front) { //delete front node
				front = front->next;
				free(current);
			} //end else
			else if (current == last) {//delete last node
				free(current);
				previous->next = NULL;
				last = previous;
			}
			else {//delete node in middle of list
				previous->next = current->next;
				free(current);
			} //end else
			printf("Book ID with value %s has been deleted\n", aBook);
		}//end else
	}//end else
}// end deleteNode




//View All Books
void viewAllNodes() {
	struct LinearNode *current;

	printf("\n");
	if (isEmpty())
		printf("Error - there are no Books in the list\n");
	else {
		current = front;
		//Loop to show all books
		while (current != NULL) {
			printf("Book ID: %d\n", current->element->bookId);
			printf("Book Name: %s\n", current->element->bookName);
			printf("Book Author: %s\n", current->element->bookAuthor);
			printf("Year of Publication: %d\n", current->element->yearOfP);
			printf("Book Status: %b\n", current->element->status);
			printf("Customer Name: %s\n", current->element->custName);
			printf("Number of times Loaned: %d\n", current->element->numOfLoans);
			current = current->next;
		} //end while
	}//end else
} //end viewAllNodes


// View Single Book
void viewSingleNode()
{
	char aNumber[10];
	struct LinearNode *current, *previous;
	bool notFound = true;

	printf("\n");
	if (isEmpty())
		printf("Error - there are no books in the list\n");
	else {
		printf("Please enter the book ID you are searching for:");
		scanf("%d", &aNumber);
		getchar;

		current = previous = front;

		while (notFound && current != NULL) {
			if (strcmp(current->element->bookId, aNumber))
				notFound = false;
			else {
				previous = current;
				current = current->next;
			}//end else
		} //end while

		if (notFound)
			printf("Error - there is not such node with value %s\n", aNumber);
		else {
			printf("Book ID: %d\n", current->element->bookId);
			printf("Book Name: %s\n", current->element->bookName);
			printf("Book Author: %s\n", current->element->bookAuthor);
			printf("Year of Publication: %d\n", current->element->yearOfP);
			printf("Book Status: %b\n", current->element->status);
			printf("Customer Name: %s\n", current->element->custName);
			printf("Number of times Loaned: %d\n", current->element->numOfLoans);
		} //end else

	}//end else
}



//display most and least popular
void displayPopular()
{
	struct LinearNode *current, *maxBook = NULL, *minBook = NULL;
	int max = 0;
	int min = INT_MAX;
	printf("\n");
	if (isEmpty())
		printf("\n\n Error - there are no books in the list\n");
	else {
		current = front;
		//searching for the most popular
		while (current != NULL) {

			if (max > current->element->numOfLoans)
			{
				max = current->element->numOfLoans;
				maxBook = current;
			}

			current = current->next;


		} //end while

		printf("Smallest node value is %d\n", max);

		printf("Book ID: %s\n", maxBook->element->bookId);
		printf("Book Name: %s\n", maxBook->element->bookName);
		printf("Book Author: %s\n", maxBook->element->bookAuthor);
		printf("Year of Publication: %d\n", maxBook->element->yearOfP);
		printf("Book Status: %d\n", maxBook->element->status);
		printf("Customer Name: %s\n", maxBook->element->custName);
		printf("Number of times Loaned: %d\n", maxBook->element->numOfLoans);


		current = front;
		//searching for the least popular
		while (current != NULL) {

			if (min < current->element->numOfLoans)
			{
				min = current->element->numOfLoans;
				minBook = current;
			}

			current = current->next;


		} //end while


		printf("\n");
		printf("\nSmallest book value is %d\n", min);
		printf("\nBook ID: %d\n", minBook->element->bookId);
		printf("\nBook Name: %s\n", minBook->element->bookName);
		printf("\nBook Author: %s\n", minBook->element->bookAuthor);
		printf("\nYear of Publication: %d\n", minBook->element->yearOfP);
		printf("\nBook Status: %b\n", minBook->element->status);
		printf("\nCustomer Name: %s\n", minBook->element->custName);
		printf("\nNumber of times Loaned: %d\n", minBook->element->numOfLoans);
	}//end else

	getchar();
	getchar();
}

void rentARoom()
{
	char response[5];
	int time = NULL;
	printf("Would you like to rent a room?(yes/no)");
	scanf(scanf("%s", response));

	do {
		if (strcmp(response, "yes") == 0)
		{
			printf("What Time would you like?(1-12)");
			printf("1. 9:00 - 10:00");
			printf("2. 10:00 - 11:00");
			printf("3. 11:00 - 12:00 ");
			printf("4. 12:00 - 13:00");
			printf("5. 13:00 - 14:00");
			printf("6. 14:00 - 15:00");
			printf("7. 15:00 - 16:00");
			printf("8. 16:00 - 17:00");
			printf("9. 17:00 - 18:00");
			printf("10. 18:00 - 19:00");
			printf("11. 19:00 - 20:00");
			printf("12. 20:00 - 21:00");
			scanf("%d", time);
			switch (time)
			{
			case 1:
				printf("\nYou have booked a room for 9:00 - 10:00");
				break;
			case 2:
				printf("\nYou have booked a room for 10:00 - 11:00");
				break;
			case 3:
				printf("\nYou have booked a room for 11:00 - 12:00");
				break;
			case 4:
				printf("\nYou have booked a room for 12:00 - 13:00");
				break;
			case 5:
				printf("\nYou have booked a room for 13:00 - 14:00");
				break;
			case 6:
				printf("\nYou have booked a room for 14:00 - 15:00");
				break;
			case 7:
				printf("\nYou have booked a room for 15:00 - 16:00");
				break;
			case 8:
				printf("\nYou have booked a room for 16:00 - 17:00");
				break;
			case 9:
				printf("\nYou have booked a room for 17:00 - 18:00");
				break;
			case 10:
				printf("\nYou have booked a room for 18:00 - 19:00");
				break;
			case 11:
				printf("\nYou have booked a room for 19:00 - 20:00");
				break;
			case 12:
				printf("\nYou have booked a room for 20:00 - 21:00");
				break;
			default: printf("\nError - no such menu option");
			}
		}
		else(strcmp(response, "no") != 0);
		{
			printf("Thank you Come back again soon");
		}
	} while (true);
}

/*
void checkIfTheFileExist() {

	outfile = fopen("book.dat", "r+b");
	if (outfile != NULL)
	{
		// file exists
		readBooksFromFile();
		printf(" Hello User! \n The system has successfully populate the db. \n\n");
		fclose(outfile);
	}else{
		printf(" Hello User! \n The Database book doesn't exist. \n Please input a book! \n\n");
	}
}

void writeBooksIntoFiles() {
	outfile = fopen("book.dat", "wb");
	struct LinearNode *current = front;

	while (current != NULL) {
		fwrite(current->element, sizeof(struct book), 1, outfile);
		current = current->next;
	}
	fclose(outfile);
}

void readBooksFromFile() {
	infile = fopen("book.dat", "rb");
	struct LinearNode *aNode;
	struct book *anElement;
	anElement = (struct book *)malloc(sizeof(struct book));

	while (fread(anElement, sizeof(struct book), 1, infile)) {
		//create space for an element

	// create space for new node that will contain data
	aNode = (struct LinearNode *)malloc(sizeof(struct LinearNode));

	if (aNode == NULL)
		printf("Error - no space for the new node\n");

	else { // add data part to the node
		aNode->element = anElement;
		aNode->next = NULL;

		//add node to end of the list
		if (isEmpty())
		{
			front = aNode;
			last = aNode;
		}
		else {
			last->next = aNode;
			last = aNode;
		} //end else
	}//end else
	anElement = (struct book *)malloc(sizeof(struct book));
}//end while
	fclose(infile);

	*/

	//Error Ask Catherine
bool isEmpty() {
	if (front == NULL)
		return true;
	else
		return false;
}