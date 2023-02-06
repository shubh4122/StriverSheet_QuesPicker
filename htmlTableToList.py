import requests
from bs4 import BeautifulSoup

# Make a request to the URL
url = "https://takeuforward.org/interviews/strivers-sde-sheet-top-coding-interview-problems/"
response = requests.get(url)

# Parse the HTML content of the page
soup = BeautifulSoup(response.content, "html.parser")

# Find all the tables on the page
tables = soup.find_all("table")

# Iterate through each table
for table in tables:
    # print("----------------------------------------------------------------")
    # print("Table:")

    # Iterate through the rows of the table
    for row in table.find_all("tr"):
        cells = row.find_all("td")

        # Extract the text from each cell and print it
        # for cell in cells:
        if len(cells) > 0: 
            print(cells[1].text)
