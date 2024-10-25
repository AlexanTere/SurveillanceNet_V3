
## Overview

The Suspect Communication Analysis Tool is a Java-based application designed to analyze and visualize communication data 
between suspects in a criminal investigation context. The tool helps users identify connections between suspects based
 on phone calls and SMS messages, providing insights into possible collaborations and threats.

## Features

- **Suspect Management**: Add, view, and search for suspects, including their names, code names, origins, and phone numbers.
- **Communication Tracking**: Record and analyze phone calls and SMS messages between suspects.
- **Visualization**: Graphical representation of suspect networks, showing relationships and potential partners.
- **Message Filtering**: Search for suspicious messages containing keywords such as "Bomb", "Attack", "Explosives", and "Gun".

## Technologies Used

- Java Swing for the graphical user interface
- JUNG (Java Universal Network/Graph Framework) for graph visualization
- Collections framework for managing data

## Classes Overview

### Communication Class Hierarchy
- **Communication**: Abstract class representing a general communication.
- **PhoneCall**: Represents a phone call with duration information.
- **SMS**: Represents an SMS message with text content.

### Suspect Class
- Manages suspect information, including phone numbers and partnerships. It can suggest partners based on the connections of known partners.

### Registry Class
- Maintains lists of suspects and communications. Provides methods to add suspects, find relationships, and retrieve communication data.

### FindSuspectPanel
- GUI panel for searching and displaying information about a specific suspect.

### SuspectPanel
- Displays detailed information about a suspect, including partners and SMS messages.

### VisualizeGraph
- Creates a visual representation of the suspect network using the JUNG library, including calculating and displaying the network's diameter.

## Installation and Usage

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd suspect-communication-analysis
   ```

2. **Compile the Code**:
   Make sure you have JDK installed. Compile the Java files:
   ```bash
   javac *.java
   ```

3. **Run the Application**:
   Run the main class (ensure you have all necessary classes):
   ```bash
   java MainClass
   ```

4. **Interact with the GUI**:
   - Use the search functionality to find suspects by name.
   - View details about suspects, including possible partners and suspicious messages.
   - Visualize the suspect network to understand relationships better.

## Example Usage

1. Add suspects and their phone numbers.
2. Record communications (both SMS and Phone Calls).
3. Use the interface to analyze connections and visualize the data.

