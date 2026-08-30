# Parking Lot System - API Documentation

## Overview
This document provides comprehensive API documentation for the Parking Lot System. The API follows REST principles and uses JSON for data exchange.

## Base URL
```
https://api.parkinglot.com/v1
```

## Authentication
All API endpoints require authentication using Bearer tokens:
```
Authorization: Bearer <your-token>
```

## Common Response Format

### Success Response
```json
{
    "success": true,
    "data": {
        // Response data
    },
    "timestamp": "2024-01-15T10:30:00Z"
}
```

### Error Response
```json
{
    "success": false,
    "error": {
        "code": "ERROR_CODE",
        "message": "Human readable error message",
        "details": {
            // Additional error details
        }
    },
    "timestamp": "2024-01-15T10:30:00Z"
}
```

---

## Parking Operations

### 1. Park Vehicle

**Endpoint:** `POST /parking-lots/{lotId}/park`

**Description:** Parks a vehicle in the specified parking lot and returns a ticket.

**Path Parameters:**
- `lotId` (string, required): Parking lot identifier

**Request Body:**
```json
{
    "vehicleId": 123,
    "licensePlate": "ABC123",
    "vehicleType": "SMALL",
    "ownerName": "John Doe"
}
```

**Response:**
```json
{
    "success": true,
    "data": {
        "ticketId": "TICKET-000001",
        "parkingSpotId": 1,
        "entryTime": "2024-01-15T10:30:00Z",
        "estimatedFare": 15.0,
        "vehicle": {
            "id": 123,
            "licensePlate": "ABC123",
            "type": "SMALL",
            "ownerName": "John Doe"
        }
    },
    "timestamp": "2024-01-15T10:30:00Z"
}
```

**Error Codes:**
- `PARKING_LOT_FULL`: No available spots for the vehicle type
- `VEHICLE_ALREADY_PARKED`: Vehicle is already parked
- `INVALID_VEHICLE_TYPE`: Invalid vehicle type provided
- `INVALID_LICENSE_PLATE`: Invalid license plate format

**Example Usage:**
```bash
curl -X POST "https://api.parkinglot.com/v1/parking-lots/PL001/park" \
  -H "Authorization: Bearer your-token" \
  -H "Content-Type: application/json" \
  -d '{
    "vehicleId": 123,
    "licensePlate": "ABC123",
    "vehicleType": "SMALL",
    "ownerName": "John Doe"
  }'
```

### 2. Unpark Vehicle

**Endpoint:** `POST /parking-lots/{lotId}/unpark`

**Description:** Unparks a vehicle and calculates the total fare.

**Path Parameters:**
- `lotId` (string, required): Parking lot identifier

**Request Body:**
```json
{
    "ticketId": "TICKET-000001"
}
```

**Response:**
```json
{
    "success": true,
    "data": {
        "ticketId": "TICKET-000001",
        "exitTime": "2024-01-15T12:30:00Z",
        "totalFare": 25.0,
        "durationHours": 2,
        "vehicle": {
            "id": 123,
            "licensePlate": "ABC123",
            "type": "SMALL",
            "ownerName": "John Doe"
        },
        "parkingSpotId": 1
    },
    "timestamp": "2024-01-15T12:30:00Z"
}
```

**Error Codes:**
- `INVALID_TICKET`: Ticket ID is invalid or expired
- `TICKET_ALREADY_USED`: Ticket has already been used for exit
- `VEHICLE_NOT_FOUND`: Vehicle not found in parking lot

**Example Usage:**
```bash
curl -X POST "https://api.parkinglot.com/v1/parking-lots/PL001/unpark" \
  -H "Authorization: Bearer your-token" \
  -H "Content-Type: application/json" \
  -d '{
    "ticketId": "TICKET-000001"
  }'
```

### 3. Unpark Vehicle by License Plate

**Endpoint:** `POST /parking-lots/{lotId}/unpark/vehicle`

**Description:** Unparks a vehicle using its license plate.

**Path Parameters:**
- `lotId` (string, required): Parking lot identifier

**Request Body:**
```json
{
    "licensePlate": "ABC123"
}
```

**Response:** Same as unpark by ticket ID

**Error Codes:**
- `VEHICLE_NOT_PARKED`: Vehicle is not parked in this lot
- `MULTIPLE_VEHICLES_FOUND`: Multiple vehicles with same license plate

---

## Query Operations

### 1. Get Parking Lot Status

**Endpoint:** `GET /parking-lots/{lotId}/status`

**Description:** Returns the current status of the parking lot.

**Path Parameters:**
- `lotId` (string, required): Parking lot identifier

**Response:**
```json
{
    "success": true,
    "data": {
        "lotId": "PL001",
        "name": "Downtown Mall Parking",
        "location": "123 Main St, Downtown",
        "totalSpots": 100,
        "availableSpots": {
            "SMALL": 20,
            "MEDIUM": 15,
            "LARGE": 10
        },
        "occupiedSpots": 55,
        "activeTickets": 55,
        "occupancyRate": 0.55,
        "lastUpdated": "2024-01-15T10:30:00Z"
    },
    "timestamp": "2024-01-15T10:30:00Z"
}
```

**Example Usage:**
```bash
curl -X GET "https://api.parkinglot.com/v1/parking-lots/PL001/status" \
  -H "Authorization: Bearer your-token"
```

### 2. Get Available Spots

**Endpoint:** `GET /parking-lots/{lotId}/spots/available`

**Description:** Returns all available parking spots.

**Path Parameters:**
- `lotId` (string, required): Parking lot identifier

**Query Parameters:**
- `vehicleType` (string, optional): Filter by vehicle type (SMALL, MEDIUM, LARGE)

**Response:**
```json
{
    "success": true,
    "data": {
        "spots": [
            {
                "spotId": 1,
                "spotNumber": "A1",
                "vehicleType": "SMALL",
                "isAvailable": true,
                "location": "Level 1, Section A"
            },
            {
                "spotId": 2,
                "spotNumber": "A2",
                "vehicleType": "SMALL",
                "isAvailable": true,
                "location": "Level 1, Section A"
            }
        ],
        "totalAvailable": 45
    },
    "timestamp": "2024-01-15T10:30:00Z"
}
```

**Example Usage:**
```bash
curl -X GET "https://api.parkinglot.com/v1/parking-lots/PL001/spots/available?vehicleType=SMALL" \
  -H "Authorization: Bearer your-token"
```

### 3. Get Ticket Information

**Endpoint:** `GET /parking-lots/{lotId}/tickets/{ticketId}`

**Description:** Returns detailed information about a specific ticket.

**Path Parameters:**
- `lotId` (string, required): Parking lot identifier
- `ticketId` (string, required): Ticket identifier

**Response:**
```json
{
    "success": true,
    "data": {
        "ticketId": "TICKET-000001",
        "status": "ACTIVE",
        "vehicle": {
            "id": 123,
            "licensePlate": "ABC123",
            "type": "SMALL",
            "ownerName": "John Doe"
        },
        "parkingSpotId": 1,
        "entryTime": "2024-01-15T10:30:00Z",
        "exitTime": null,
        "currentFare": 15.0,
        "durationHours": 1,
        "baseFare": 10.0,
        "hourlyRate": 5.0
    },
    "timestamp": "2024-01-15T10:30:00Z"
}
```

**Error Codes:**
- `TICKET_NOT_FOUND`: Ticket not found
- `INVALID_TICKET_ID`: Invalid ticket ID format

**Example Usage:**
```bash
curl -X GET "https://api.parkinglot.com/v1/parking-lots/PL001/tickets/TICKET-000001" \
  -H "Authorization: Bearer your-token"
```

### 4. Get Vehicle Information

**Endpoint:** `GET /parking-lots/{lotId}/vehicles/{licensePlate}`

**Description:** Returns information about a parked vehicle.

**Path Parameters:**
- `lotId` (string, required): Parking lot identifier
- `licensePlate` (string, required): Vehicle license plate

**Response:**
```json
{
    "success": true,
    "data": {
        "vehicle": {
            "id": 123,
            "licensePlate": "ABC123",
            "type": "SMALL",
            "ownerName": "John Doe"
        },
        "isParked": true,
        "parkingSpotId": 1,
        "entryTime": "2024-01-15T10:30:00Z",
        "currentFare": 15.0,
        "ticketId": "TICKET-000001"
    },
    "timestamp": "2024-01-15T10:30:00Z"
}
```

**Example Usage:**
```bash
curl -X GET "https://api.parkinglot.com/v1/parking-lots/PL001/vehicles/ABC123" \
  -H "Authorization: Bearer your-token"
```

---

## Fare Operations

### 1. Calculate Fare

**Endpoint:** `POST /parking-lots/{lotId}/calculate-fare`

**Description:** Calculates fare for a given vehicle type and duration.

**Path Parameters:**
- `lotId` (string, required): Parking lot identifier

**Request Body:**
```json
{
    "vehicleType": "SMALL",
    "durationHours": 3
}
```

**Response:**
```json
{
    "success": true,
    "data": {
        "vehicleType": "SMALL",
        "durationHours": 3,
        "baseFare": 10.0,
        "hourlyRate": 5.0,
        "totalFare": 25.0,
        "breakdown": {
            "baseFare": 10.0,
            "hourlyCharges": 15.0,
            "total": 25.0
        }
    },
    "timestamp": "2024-01-15T10:30:00Z"
}
```

**Example Usage:**
```bash
curl -X POST "https://api.parkinglot.com/v1/parking-lots/PL001/calculate-fare" \
  -H "Authorization: Bearer your-token" \
  -H "Content-Type: application/json" \
  -d '{
    "vehicleType": "SMALL",
    "durationHours": 3
  }'
```

### 2. Get Fare Configuration

**Endpoint:** `GET /parking-lots/{lotId}/fare-config`

**Description:** Returns the current fare configuration for the parking lot.

**Path Parameters:**
- `lotId` (string, required): Parking lot identifier

**Response:**
```json
{
    "success": true,
    "data": {
        "baseFares": {
            "SMALL": 10.0,
            "MEDIUM": 20.0,
            "LARGE": 30.0
        },
        "hourlyRates": {
            "SMALL": 5.0,
            "MEDIUM": 10.0,
            "LARGE": 15.0
        },
        "effectiveFrom": "2024-01-01T00:00:00Z",
        "lastUpdated": "2024-01-15T10:30:00Z"
    },
    "timestamp": "2024-01-15T10:30:00Z"
}
```

**Example Usage:**
```bash
curl -X GET "https://api.parkinglot.com/v1/parking-lots/PL001/fare-config" \
  -H "Authorization: Bearer your-token"
```

---

## Analytics Operations

### 1. Get Parking Analytics

**Endpoint:** `GET /parking-lots/{lotId}/analytics`

**Description:** Returns analytics data for the parking lot.

**Path Parameters:**
- `lotId` (string, required): Parking lot identifier

**Query Parameters:**
- `startDate` (string, optional): Start date for analytics (ISO 8601 format)
- `endDate` (string, optional): End date for analytics (ISO 8601 format)
- `groupBy` (string, optional): Group by day, hour, or vehicle type

**Response:**
```json
{
    "success": true,
    "data": {
        "period": {
            "startDate": "2024-01-01T00:00:00Z",
            "endDate": "2024-01-15T23:59:59Z"
        },
        "summary": {
            "totalVehicles": 1500,
            "totalRevenue": 25000.0,
            "averageDuration": 2.5,
            "peakHours": ["09:00", "17:00"],
            "mostPopularVehicleType": "SMALL"
        },
        "dailyStats": [
            {
                "date": "2024-01-15",
                "vehiclesParked": 100,
                "revenue": 1500.0,
                "averageDuration": 2.3
            }
        ],
        "vehicleTypeStats": {
            "SMALL": {
                "count": 800,
                "revenue": 12000.0,
                "averageDuration": 2.0
            },
            "MEDIUM": {
                "count": 500,
                "revenue": 10000.0,
                "averageDuration": 3.0
            },
            "LARGE": {
                "count": 200,
                "revenue": 3000.0,
                "averageDuration": 4.0
            }
        }
    },
    "timestamp": "2024-01-15T10:30:00Z"
}
```

**Example Usage:**
```bash
curl -X GET "https://api.parkinglot.com/v1/parking-lots/PL001/analytics?startDate=2024-01-01&endDate=2024-01-15&groupBy=day" \
  -H "Authorization: Bearer your-token"
```

---

## Error Codes Reference

| Error Code | Description | HTTP Status |
|------------|-------------|-------------|
| `PARKING_LOT_FULL` | No available spots for vehicle type | 409 Conflict |
| `VEHICLE_ALREADY_PARKED` | Vehicle is already parked | 409 Conflict |
| `INVALID_TICKET` | Ticket ID is invalid or expired | 400 Bad Request |
| `TICKET_ALREADY_USED` | Ticket has already been used | 400 Bad Request |
| `VEHICLE_NOT_PARKED` | Vehicle is not parked | 404 Not Found |
| `INVALID_VEHICLE_TYPE` | Invalid vehicle type provided | 400 Bad Request |
| `INVALID_LICENSE_PLATE` | Invalid license plate format | 400 Bad Request |
| `PARKING_LOT_NOT_FOUND` | Parking lot not found | 404 Not Found |
| `UNAUTHORIZED` | Authentication required | 401 Unauthorized |
| `FORBIDDEN` | Insufficient permissions | 403 Forbidden |
| `INTERNAL_SERVER_ERROR` | Internal server error | 500 Internal Server Error |

---

## Rate Limiting

The API implements rate limiting to ensure fair usage:

- **Standard Plan**: 1000 requests per hour
- **Premium Plan**: 10000 requests per hour
- **Enterprise Plan**: Custom limits

Rate limit headers are included in responses:
```
X-RateLimit-Limit: 1000
X-RateLimit-Remaining: 999
X-RateLimit-Reset: 1642233600
```

---

## SDK Examples

### Java SDK
```java
ParkingLotClient client = new ParkingLotClient("your-api-key");

// Park a vehicle
ParkRequest request = new ParkRequest();
request.setVehicleId(123);
request.setLicensePlate("ABC123");
request.setVehicleType(VehicleType.SMALL);
request.setOwnerName("John Doe");

ParkResponse response = client.parkVehicle("PL001", request);
System.out.println("Ticket ID: " + response.getTicketId());

// Unpark a vehicle
UnparkResponse unparkResponse = client.unparkVehicle("PL001", response.getTicketId());
System.out.println("Total Fare: $" + unparkResponse.getTotalFare());
```

### Python SDK
```python
from parking_lot_sdk import ParkingLotClient

client = ParkingLotClient("your-api-key")

# Park a vehicle
response = client.park_vehicle("PL001", {
    "vehicleId": 123,
    "licensePlate": "ABC123",
    "vehicleType": "SMALL",
    "ownerName": "John Doe"
})

print(f"Ticket ID: {response['ticketId']}")

# Unpark a vehicle
unpark_response = client.unpark_vehicle("PL001", response['ticketId'])
print(f"Total Fare: ${unpark_response['totalFare']}")
```

### JavaScript SDK
```javascript
const ParkingLotClient = require('parking-lot-sdk');

const client = new ParkingLotClient('your-api-key');

// Park a vehicle
const response = await client.parkVehicle('PL001', {
    vehicleId: 123,
    licensePlate: 'ABC123',
    vehicleType: 'SMALL',
    ownerName: 'John Doe'
});

console.log(`Ticket ID: ${response.ticketId}`);

// Unpark a vehicle
const unparkResponse = await client.unparkVehicle('PL001', response.ticketId);
console.log(`Total Fare: $${unparkResponse.totalFare}`);
```

---

This API documentation provides comprehensive coverage of all parking lot system operations, including detailed request/response formats, error handling, and usage examples. 