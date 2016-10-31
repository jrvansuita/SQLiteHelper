[![Release](https://jitpack.io/v/jrvansuita/SQLiteHelper.svg)](https://jitpack.io/#jrvansuita/SQLiteHelper)

# SQLiteHelper 
This Parser comes in handy when you want to write a sql statement easily and smarter.

#Porpouse

Make things easy when you need to write a sql statment for Android SQLite.

# Usage

#### Step 1. Add the JitPack repository to your build file:

    allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}

#### Step 2. Add the dependency

    dependencies {
	        compile 'com.github.jrvansuita:SQLiteHelper:v1.0.0'
	}

# Samples
 You can take a look at the sample app [located on this project](/app/).

# Implementation

### Select

##### Working with columns.   

    SqlParser.query()
       .col("A")
       .col("B")
       .col("C", "NICK")
       .col("ALIAS","D", "NICK")
       .cols("E", "F", "G")
       .sum("H").count()
       .max("I")
       .table("YOUR_TABLE", "T")
       .build();

   > Output: SELECT A, B, C AS NICK, ALIAS.D AS NICK, E, F, G, SUM(H), COUNT(*), MAX(I) FROM  YOUR_TABLE T


##### More than one table.   

     SqlParser.query()
        .col("P", "NAME", "PRODUCT_NAME")
        .col("C", "NAME", "COLOR_NAME")
        .table("PRODUCT", "P")
        .table("COLOR", "C")
        .equal("P", "IDCOLOR", "C","ID")
        .build();

  > Output: SELECT P.NAME AS PRODUCT_NAME, C.NAME AS COLOR_NAME FROM PRODUCT P, COLOR C WHERE P.IDCOLOR = C.ID


##### Exists or not exists.   
   
        SqlParser.query()
           .table("TABLE", "T")
           .exists(Sql.query().table("XTABLE", "XT").equal("XT", "FIELD", "T","FIELD").build())
           .notExists(Sql.query().table("YTABLE", "YT").equal("YT", "FIELD", "T","FIELD").build())
           .build();
  
  > Output: SELECT  *  FROM  TABLE T WHERE  EXISTS (SELECT  *  FROM  XTABLE XT WHERE XT.FIELD = T.FIELD) NOT EXISTS (SELECT  *  FROM  YTABLE YT WHERE YT.FIELD = T.FIELD)
  
  
##### Greater, smaller, equal, trim.   
  
      SqlParser.query()
         .table("TABLE")
         .greater("THE_COLUMN" , 9)
         .and()
         .smallerEqual("THE_COLUMN", 40)
         .or()
         .equalTrim("TEST", " RAW ")
         .build();
       
   > Output: SELECT  *  FROM  TABLE WHERE THE_COLUMN > 9 AND THE_COLUMN <= 40 OR TRIM(TEST) = 'RAW'
       
### Delete.

     SqlParser.delete("TABLE").smallerEqual("COL", 0).build();     
     
   > Output: DELETE FROM TABLE WHERE COL <= 0
   
### Insert.
   
      SqlParser.insert("TABLE")
         .col("A", 1)
         .col("B", "TEST")
         .build();

> Output: INSERT INTO TABLE(A,B) VALUES(1,'TEST');
         
### Create.

     SqlParser.create("TABLE")
                .pk("ID")
                .num("CODE")
                .num("TYPE")
                .flo("PRICE")
                .flo("QUANTITY")
                .build();
                
  > Output: CREATE TABLE TABLE (ID INTEGER PRIMARY KEY,CODE INTEGER,TYPE INTEGER,PRICE FLOAT,QUANTITY FLOAT);

         
### Cursor.

     Cursor cp = SqlParser.cursor(yourCursor);

        if (cp.binded()) 
            Product product = new Product(cp.num("ID")
                                        , cp.num("CODE")
                                        , cp.flo("STOCK")
                                        , cp.flo("")
                                        , cp.str("NAME"));
                                        
                                        
### ContentValues.
       
	 SqlParser.content().add("NAME", "John")
		          .add("CITY", "New York")
        		  .add("STATE", "New Jersey");
        		  
