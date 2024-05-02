# Components

## 1. Remote Interface (Adder)

### Purpose

To provide an Interface (a.k.a API, Function Signatures) so that the Server and Client can be certain that they share the some functionality.

Basically, Java wants to be 100% sure that you don't make a mistake.
Something that happens all the time in JavaScript.

Below is the Interface for the a method called "Adder" (because it adds staff)
With this Java knows the behavior of this method.

Specifically it knows that:

- It is a public method, thus can be called by other Classes.
- Is meant to be called Remotely, because it extends the Remote Class
- It returns an interger
- The method's name is "Adder"
- The method has 2 parameters (x, y) and their types (int, int)
- This method might throw a "RemoteException" Exception (a.k.a Error), basically it even know what type of error this method can throw.

```java
public interface Adder extends Remote {
    int add(int x, int y) throws RemoteException;
}
```

## 2. Interface Implimentation (AdderImpl)

### Purpose

This file is responsible for implementing (creating) the Business Logic (the actual functionality) of the application.

Let's see what is the purpose of every statement.

```java
package simple_rmi_project;
```

**_Packages_** are used to organize code, and it creates separate namespaces for each package. Namespaces are used to ensure that there are no naming conflicts between Classes. A naming conflict occurs then 2 Classes share the same name. This can happen quite easily in large codebases (hundreds of thousands lines of code) where many programmers work together.

```java
import java.rmi.RemoteException;
```

The **_RemoteException_** Class from the **_rmi package_** is an exception class. This means that we use it to create specific types of errors (exceptions). Here is an example:

```java
// Some Method in a Class
public void login(int userId) throws RemoteException {
    // Some logic here
    boolean success = fetchUserData(userId); // Example condition

    if (!success) {
        throw new RemoteException("Failed to fetch user data.");
    }
}
```

The **UnicastRemoteObject** Class, which comes from the **server package**, which in turn comes from the **rmi package**, is responsible for supplying the required fields and methods to make a Class's Instance a remote object. Simply put, it allows/enables the Class to be called remotely.

This is achieved through **inheritance**, a feature that many OOP (Object-Oriented Programming) languages share. Through inheritance, a class can obtain fields and methods from another Class. In our case, the **UnicastRemoteObject** is our base or parent class, and by using the **extends** keyword we inherit some of its methods and fields. It's those methods and fields, that allow our Class (AddrImpl) to be called Remotely.

```java
import java.rmi.server.UnicastRemoteObject;
```

Next, we have the Class definition. This tells the java compiler important information about our class and also how we want it to be treated.

First, the **public** keyword is an access modifier, this will allow other Classes to access our Class's methods and fields (a.k.a variables, properties, or attributes).

Here is clear that we inherit from the **UnicastRemoteObject** Class (because we use the _extends_ keyword) and is also clear that this Class implements the Adder Interface.

**Implemeting an Interface** means that you must satisfy the interface's restrictions. In our case, the interface only requires that we create (implement) a method called "add" with 2 parameters (int x, int y) and that the method should return an int. It also requires that if this method might throw an exception, that exception must be of type _RemoteException_. Finally, it wants our Class to be a sub-class or inherit from the _Remote Class_.

```java
public class AdderImpl extends UnicastRemoteObject implements Adder
```

In the implementation below, we satisfy all the Adder Interface's restrictions. You might be thinking: _"We do not extend from the Remote Class, instead we extend from the UnicastRemoteObject. Why does this work?_

This works because the UnicastRemoteObject Class is a sub-class of the Remote Class. Thus, it has all the Remote Class's variables and methods. This is way it satisfies the Adder Interface.

```java
public class AdderImpl extends UnicastRemoteObject implements Adder {

    protected AdderImpl() throws RemoteException {
        super();
    }

    public int add(int x, int y) {
        return x + y;
    }
}
```

You might also be thinking, what does this do?

```java
    protected AdderImpl() throws RemoteException {
        super();
    }
```

This specific method that shares that same name with the Class, is called the Class's constructor and its a special method. Its purpose is to intialize the Class. Here is an example of a GameEntity and a Player Class that could be used in a Video Game:

### GameEntity Class (The Parent Class)

```java
import java.util.Random;

public class GameEntity {
    public int id;
    public String name;
    private int hp;

    public GameEntity(String name) throws Exception {
        this.id = generateRandomId(id);
        this.name = name;
        this.hp = 100;
    }

    private int generateRandomId() {
        Random random = new Random();
        return random.nextInt(10000);
    }

    public getHP() {
        return hp
    }
}
```

### Player Class (The Child Class)

```java
import java.util.List;

public class Player extends GameEntity {
    private int level;
    private List<GameItem> inventory // Don't pay attention to the Types here

    public Player(String name, List<GameItem> items) throws Exception {
        super(name); // When inheriting, this must be the first statement in the constructor
        this.level = 1;
        this.inventory = items;
    }

    public getLevel() {
        return level;
    }

    public getInventory() {
        return inventory;
    }
}
```

As you can see we have 2 Classes, the GameEntity and the Player. This is how we create a new Player in our code:

```java
Player player1 = new Player("Player 1", starterItems)
```

By calling **_new Player("Player 1", starterItems)_** we basically call the Player Class's constructor. And inside the Player constructor, the first statement is the **super**(name). This will call the constructor of the Parent Class (the GameEntity Class), which is why we pass the name variable to it. Because the GameEntity contructor needs it in order to intiliaze the GameEntity Class.

Once this done, we can access variables and methods implemented in the GameEntity class from the Player instance like so:

```java
int currentPlayerHP = player1.getHP()
```

This is a vital part of OOP as it helps in keeping our code DRY (Don't Repeat Yourself) and also helps in creating abstractions which is super important in large codebases.

Last thing, the **protected access modifier** tells the compiler that this particular method (the constructor in our case) should not be called from neither other Classes within the same package nor from sub-classes. It should only be called from Classes from other packages.

```java
    protected AdderImpl() throws RemoteException {
        super();
    }
```

## 3. Server

## 4. Client
