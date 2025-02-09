# Checkout System

A flexible and modular checkout system for calculating the total price of items in a basket. It supports dynamic pricing and the ability to read pricing data from a CSV file.

---

## **Core Components**

### **Main**
- **Functionality:** The `Main` class is the entry point of the system. It initialises and runs the checkout process by interacting with other components like `Checkout`, `PriceCalculator`, `Basket`, and `CheckoutCLI`.
- **Rationale:** By separating the initialisation of the system into the `Main` class, we provide a clear entry point to the application while keeping other components focused on their specific responsibilities.

### **Checkout**
- **Functionality:** The `Checkout` class serves as the central component for the checkout process. It is responsible for scanning items, applying prices, and updating the total amount due.
- **Rationale:** By assigning the responsibility of managing the checkout process to the `Checkout` class, the business logic is kept separate from the user interface. This separation promotes better code organisation and maintainability.

### **PriceCalculator**
- **Functionality:** The `PriceCalculator` class calculates the total price of items in the basket, factoring in standard pricing as well as any applicable discounts.
- **Rationale:** The `PriceCalculator` is dedicated solely to pricing logic, which improves flexibility and allows for the easy addition of new pricing rules as needed.
  - **Why not in Checkout?** By separating pricing logic from the `Checkout` class, we adhere to the **Single Responsibility Principle**, ensuring that each class has a clear, specific role.

### **Basket**
- **Functionality:** The `Basket` class tracks the items that have been scanned, including their quantities, throughout the checkout process.
- **Rationale:** The `Checkout` class does not interact directly with the raw data of the basket, allowing for better organisation and simplifying testing and maintenance.

### **PriceModifier**
- **Functionality:** The `PriceModifier` class defines rules for item pricing, including unit prices and special offers (such as "3 for 130").
- **Rationale:** Encapsulating pricing rules within the `PriceModifier` class helps maintain a clean, organised system. It also simplifies the addition of new rules as the system evolves.
  - **Benefit:** The modular nature of this design allows for easy introduction of new discount types, such as seasonal offers or loyalty discounts.

### **CSVMapper**
- **Functionality:** The `CSVMapper` class reads pricing data from a CSV file and loads it into `PriceModifier` objects for use during checkout.
- **Rationale:** Using a CSV format allows non-developers to update pricing information easily, without requiring modifications to the code. This approach is also flexible, making it simple to switch to other formats (e.g., JSON or XML) if necessary.
  - **Why CSV?** CSV is a simple, widely-used format that enables quick edits without needing to alter the underlying codebase.

### **CheckoutCLI**
- **Functionality:** The `CheckoutCLI` class provides a command-line interface for the user to interact with the system. It accepts item scans, displays running totals, and presents the final total at checkout.
- **Rationale:** The command-line interface is lightweight and easy to test, offering a simple user experience. If desired, this interface can later be replaced with a more advanced web-based interface.

---

## **Flexibility and Extensibility**

### **Dynamic Pricing**
Pricing can be easily updated via the CSV file, allowing for changes to pricing rules without requiring code modifications.

### **Pricing Strategies**
The system supports various pricing strategies, including:
  - **Unit Price:** Standard price per unit for each item.
  - **Special Pricing:** Discounted price for specific quantities (e.g., "3 for 130").

This flexible design allows for easy modification and addition of new pricing strategies to meet evolving requirements.

---

## **Testing and Maintainability**

### **Test-Driven Development (TDD)**
The system was developed using **Test-Driven Development (TDD)** to ensure its reliability and stability. Key areas covered by tests include:
  - **Checkout Flow:** Scanning items, updating totals, and applying discounts.
  - **Pricing Logic:** Handling special pricing.
  - **CSV Parsing:** Ensuring the CSV data is parsed correctly and the pricing rules are applied as expected.

### **Separation of Concerns**
The system is designed to ensure that each class has a single responsibility, making it easier to maintain and extend:
  - `Checkout` manages the transaction flow.
  - `PriceCalculator` handles the calculation of prices.
  - `Basket` tracks items and quantities.
  - `PriceModifier` contains pricing rules.
  - `CSVMapper` handles the parsing of pricing data from the CSV file.
  - `CheckoutCLI` provides the user interface.

This separation of concerns simplifies maintenance and facilitates the addition of new features with minimal impact on the existing system.

---
