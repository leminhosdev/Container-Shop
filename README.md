<h1>NFT-Searcher</h1>
Creating a SpringBoot restAPI that provides the system so people can search for NFTS collections and buy/sell yours nfts, the price is updated in real time // Criando um SpringBoot restAPI que fornece o sistema para que as pessoas possam pesquisar coleções NFTS e comprar/vender as suas nfts, o preço é atualizado em tempo real

# status: devoloping🚧

First, the user can choose if he wants to create an account or sig in, Lets see this step by step.

## 1. Creating account
that are the variables necessary to create a account.

* E-mail
* Name
* Password
* CPF

## 2. Sig In
with your account created, you will need authenticate your datas in the system, using the logIn screen but just using 2 variables now.

* Email
* Password

## 3. Searching for collections
This is the main screen, here you will be able to search for collectons like Azuki, MoonBirds, the Captainz and others. The results of the searchs will datas like:

* FloorPrice 
* Trading Volume
* Owners
 
Some actions like buying a nft, are working but aren't 100%, i'm still working on that, that its just he firsts steps, we will talk about this in the next topic.  

## Futures updates

* Solve bugs
* Improving buy Option, and add Sell option
* Allow that the user can see your profit/loss in real time
* Create a page witch you will can put profile pictures, and see your collections in the wallet

## Tecnologys used in this project 

<table>
 <tr>
 <td> Java </td>
 <td> SpringBoot</td>
 <td> Hibernate</td>
 <td> Docker </td>
 <td> MySql</td>
 <td> Junit </td>
 </tr>
</table>

## How to run the aplication?? ⚙

create a DataBase with:

Name: containerMarket
Password: root

the ports used were "3300:3306" "3300" must be free in your computer or you will need to change the porst in configs

* application.yml
* docker-compose.yml

Now, run this command in terminal:

*docker-compose up 

Start application runing the main class, and search for "http://localhost:8080/inicio" in your web navigator;


## Final Comments 📈

This project will be updated, stay stuned for news, and follow me here and in social midias or if you want to contact me:

* Linkedin :https://www.linkedin.com/in/lucas-lemos-b5879625b/
* instagram: https://www.instagram.com/lucas_lemosl/
* email: lucaslemos8294@gmail.com




