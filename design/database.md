For simplicity and flexibility we going to use H2 database for this project.

## Entity Diagram

```mermaid
erDiagram
    USER {
        long id PK
        string user_name UK
        string password
    }

    BOOK {
        string isbn PK
        string title
        string author
        string publisher
        int published_year
        string genre
    }
```

UNIQUE(title, author): This constraint ensures that there cannot be multiple entries in the **books** table with the same
title by the same author.

