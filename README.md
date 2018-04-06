In first, please spent a litle bit time to read Clean in here:
https://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/

This is an example structure how to build Clean Architecture in Android, there are 3 layers:
- app: in presentation layer (app) I use MVP architecture and dagger
- domain: in domain layer(business) it us pure Java and I use RxJava
- data: for data layer (api) I use OKHTTP,retrofit

Terminology
- Use-case - a simple business
- Repository - an interface where the use-case can access data
- RepositoryImpl - an implementation of repository
- CallBack - an interface where the use-case can notify data
- CallBackIpml - an implementation of CallBack.

Example requirement:
- After user logins, customer want to see their profile.
- If the profile is active, application shows it.
- If the customer profile is blocked by system, application shows “profile is blocked ”message to customer.
- If the customer profile is closed, application shows “profile is closed ”message to customer
