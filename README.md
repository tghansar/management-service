# management-service
A book CRUD web service back-end (use with front-end repository called ui-service)

Use Case:
---------
As a book collector I want the ability to create, and keep up to date, a catalogue of all my books. The catalogue service should provide a list of all catalogued books
from where I need to be able to add new books, update existing information and remove books.

Overview:
--------
• Provide a multi-tier catalogue solution to add, edit, delete, and view current books.
• Build two separate Spring Boot services for an UI (User Interface) and Management Layer, with supporting DB.
• UI service must integrate the Management service's API utilizing a Jax-RS Client.
• Services should be packaged as executable jars, with all dependencies included.
• Scripts (Linux or Win) for service execution will be required.

Scope:
------
UI - 1'st Service Requirements

• Functional:
• List all books
• Add a new book
• Edit an existing book
• Delete current book

• Technical:
• Spring Boot 2 Web application
• Java version: 1.8
• Maven project
• Generate HTML in the web service that is returned as a full web page to be viewed in a browser. Any of the
following technologies can be used:
  o JSP/JS (preferred)
  o Thymeleaf
  o Stringtemplate
  o Velocity
  o etc.

Management - 2'nd Service Requirements

• Functional:
• Expose a Rest Api for the following actions:
  o List
  o Add
  o Update
  o Delete
• Utilize an in-memory DB to support the above-mentioned operations.
• Book data required:
  o Name
  o ISBN Number
  o Publish date (dd/MM/yyyy)
  o Price (ZAR)
  o Book Type (Hard Cover, Soft Cover, eBook, etc.)

• Technical:
• Spring Boot 2 application
• Java version: 1.8
• Maven project
