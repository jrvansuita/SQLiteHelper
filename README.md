
 <a href='https://ko-fi.com/A406JCM' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi4.png?v=f' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>


[![Release](https://jitpack.io/v/jrvansuita/SQLiteHelper.svg)](https://jitpack.io/#jrvansuita/SQLiteHelper)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SQLiteParser-green.svg?style=true)](https://android-arsenal.com/details/1/4565)

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
         .like("TEST2", "%fox%")
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
        		  
## Update

        ContentValues cv =  SqlParser.content().add("NAME", "John")
		          .add("CITY", "New York")
        		  .add("STATE", "New Jersey").get();

         yourDb.update(tableName, cv, rowId + " = ?", new String[]{String.valueOf(yourId)});




<a href="https://www.instagram.com/jnrvans/" target="_blank">
  <img src="https://camo.githubusercontent.com/c9dacf0f25a1489fdbc6c0d2b41cda58b77fa210a13a886d6f99e027adfbd358/68747470733a2f2f6564656e742e6769746875622e696f2f537570657254696e7949636f6e732f696d616765732f7376672f696e7374616772616d2e737667" alt="Instagram" witdh="44" height="44" hspace="10">
</a>
<a href="https://github.com/jrvansuita" target="_blank">
  <img src="https://camo.githubusercontent.com/b079fe922f00c4b86f1b724fbc2e8141c468794ce8adbc9b7456e5e1ad09c622/68747470733a2f2f6564656e742e6769746875622e696f2f537570657254696e7949636f6e732f696d616765732f7376672f6769746875622e737667" alt="Github" witdh="44" height="44" hspace="10">
</a>
<a href="https://play.google.com/store/apps/dev?id=8002078663318221363" target="_blank">
  <img src="https://camo.githubusercontent.com/8ce12185c778e13eed2073e7a6aba042ce5092d4d41744e7052e0fc16363c386/68747470733a2f2f6564656e742e6769746875622e696f2f537570657254696e7949636f6e732f696d616765732f7376672f676f6f676c655f706c61792e737667" alt="Google Play Store" witdh="44" height="44" hspace="10">
</a>
<a href="mailto:vansuita.jr@gmail.com" target="_blank" >
  <img src="https://camo.githubusercontent.com/4a3dd8d10a27c272fd04b2ce8ed1a130606f95ea6a76b5e19ce8b642faa18c27/68747470733a2f2f6564656e742e6769746875622e696f2f537570657254696e7949636f6e732f696d616765732f7376672f676d61696c2e737667" alt="E-mail" witdh="44" height="44" hspace="10">
</a>
