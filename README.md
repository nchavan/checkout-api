# checkout-api

### a. Chosen language
Java

### b. How you can run code

### Installing
* Clone this repository:
git clone https://github.com/nchavan/checkout-api.git

### Command Line - Building & Running project using gradle wrapper

- Install Gradle if not already installed - brew install gradle
- Build your project with Gradle Wrapper
- gradle wrapper --gradle-version 6.7
- ./gradlew build
- ./gradlew run

### c. Notes, Assumptions, Decisions ###
### Notes ###
* App is only for Demo purpose and it contains hard coded tests data as specified in the problem statement
* App if run will only show result for the product 001, 002 & 003.
* Please check CheckoutTest for all the test scenarios mentioned in the problem statement
* To test any new test scenario you will need to add new unit test within CheckoutTest

### assumptions ###
* Total price has default currency added as '£' based on the problem statement

### Thinking behind decisions ###
* ProductDiscounts will be applied first - we can add more products discounts to the PromotionalRules.run.
* CartDiscounts will be applied at the end on the total price o Cart. We can even add more discount rules to the Cart.
* Single responsibility principal - Classes are following single responsibility principal.
* Separate interfaces for Products and Cart discounts so they can be extended further.
* Open Closed Principal - Open for extension we can add more product discounts like buy one get one free etc by creating new classes and extending it.
* Cart has an id field added as every order will have a new unique card id and within production we can use cardId to check different transactions it will be used for troubleshooting purpose

### d. Big O analysis ###
Every Order will have n number of products added, m number of promotions added for products & o number of promotions added for cards.
* products (n) - O(n)
* products promotions (m) - O(m)
* card discounts (o) - O(o)

### e. Reasons behind data structures chosen ###
* Set - sets do not allow duplicate values - product & cart discounts set will not have duplicate promotions added
* HashMap - As the insertion and access is O(1)