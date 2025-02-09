# Checkout System

A flexible and modular checkout system for calculating the total price of items in a basket. It supports dynamic pricing and the ability to read pricing data from a CSV file.

---

## **Core Components**

### **Main**
- **Functionality:** The `Main` class serves as the entry point, initializing and running the checkout process by interacting with other components like `Checkout`, `PriceCalculator`, `Basket`, and `CheckoutCLI`.
- **Rationale:** Separating initialization into `Main` provides a clear entry point while maintaining focused responsibilities for other classes.

### **Checkout**
- **Functionality:** The `Checkout` class is the central hub of the checkout process. It handles scanning items, applying prices, and calculating the total amount.
- **Rationale:** By isolating business logic from user interaction, the `Checkout` class makes the system more organized and maintainable.

### **PriceCalculator**
- **Functionality:** The `PriceCalculator` is responsible for calculating the total price of items, including standard prices and any applicable discounts.
- **Rationale:** Keeping pricing logic separate from the checkout flow follows the **Single Responsibility Principle**, making it easy to extend pricing rules.

### **Basket**
- **Functionality:** The `Basket` class tracks the items and their quantities throughout the checkout process.
- **Rationale:** The basket ensures clean data management for items scanned during checkout, simplifying testing and future updates.

### **PriceModifier**
- **Functionality:** The `PriceModifier` class applies pricing rules, such as unit prices and special offers (e.g., "3 for 130").
- **Rationale:** Isolating pricing rules in this class keeps the code organized and makes adding new rules easier.

### **CSVMapper**
- **Functionality:** The `CSVMapper` class reads pricing data from a CSV file and loads it into `PriceModifier` objects for use during checkout.
- **Rationale:** CSV is a flexible and simple format for non-developers to manage pricing data, making it easy to update without altering code.

### **CheckoutCLI**
- **Functionality:** The `CheckoutCLI` class provides a command-line interface for scanning items and displaying the total price.
- **Rationale:** The CLI is simple to use and test, and could later be replaced with a web interface if desired.

---

## **Flexibility and Extensibility**

- **Dynamic Pricing:** Pricing can be updated easily via the CSV file, without needing code changes.
- **Pricing Strategies:** The system supports multiple pricing strategies:
  - **Unit Price:** Standard price per item.
  - **Multi-buy Pricing:** Discounted prices for specific quantities.

This setup allows easy modification and addition of new pricing strategies.

---

## **Testing and Maintainability**

- **Test-Driven Development (TDD):** The system was built using TDD, ensuring its reliability. Tests cover the checkout flow, pricing logic, and CSV parsing.
- **Separation of Concerns:** Each class has a single responsibility, making the system easier to maintain and extend:
  - `Checkout`: Manages the transaction flow.
  - `PriceCalculator`: Handles pricing calculations.
  - `Basket`: Tracks items and quantities.
  - `PriceModifier`: Defines pricing rules.
  - `CSVMapper`: Handles pricing data from CSV files.
  - `CheckoutCLI`: Provides the user interface.

This separation helps ensure that the system remains maintainable as it grows.

---
