<!DOCTYPE html>
<html>
<head>
    <title>My Rentals</title>
</head>
<body>
    <h1>My Rentals</h1>
    
    <h2>Active</h2>
    <div class="rentals">
        <g:each in="${myRentals}" var="rental">
            <div class="rental-card">
                <h3>Rental ID: ${rental.id}</h3>
                <p>Start: ${new Date(rental.rentStartTimestamp * 1000)}</p>
                <p>End: ${new Date(rental.rentEndTimestamp * 1000)}</p>
                <p>Total Cost: ${rental.totalCost}</p>
                <p>Bike ID: ${rental.bikeId}</p>
                <p>Renter ID: ${rental.renterId}</p>
                <p>Completed: ${rental.rentalCompleted ? 'Yes' : 'No'}</p>
            </div>
        </g:each>
    </div>
    <h2>Historic</h2>
    <div class="rentals">
        <g:each in="${myRentals}" var="rental">
            <div class="rental-card">
                <h3>Rental ID: ${rental.id}</h3>
                <p>Start: ${new Date(rental.rentStartTimestamp * 1000)}</p>
                <p>End: ${new Date(rental.rentEndTimestamp * 1000)}</p>
                <p>Total Cost: ${rental.totalCost}</p>
                <p>Bike ID: ${rental.bikeId}</p>
                <p>Renter ID: ${rental.renterId}</p>
                <p>Completed: ${rental.rentalCompleted ? 'Yes' : 'No'}</p>
            </div>
        </g:each>
    </div>

    <h1>Owned Rentals</h1>
    <h2>Active</h2>
    <div class="rentals">
        <g:each in="${ownedRentals}" var="rental">
            <div class="rental-card">
                <h3>Rental ID: ${rental.id}</h3>
                <p>Start: ${new Date(rental.rentStartTimestamp * 1000)}</p>
                <p>End: ${new Date(rental.rentEndTimestamp * 1000)}</p>
                <p>Total Cost: ${rental.totalCost}</p>
                <p>Bike ID: ${rental.bikeId}</p>
                <p>Renter ID: ${rental.renterId}</p>
                <p>Completed: ${rental.rentalCompleted ? 'Yes' : 'No'}</p>
            </div>
        </g:each>
    </div>
    <h2>Historic</h2>
    <div class="rentals">
        <g:each in="${ownedRentals}" var="rental">
            <div class="rental-card">
                <h3>Rental ID: ${rental.id}</h3>
                <p>Start: ${new Date(rental.rentStartTimestamp * 1000)}</p>
                <p>End: ${new Date(rental.rentEndTimestamp * 1000)}</p>
                <p>Total Cost: ${rental.totalCost}</p>
                <p>Bike ID: ${rental.bikeId}</p>
                <p>Renter ID: ${rental.renterId}</p>
                <p>Completed: ${rental.rentalCompleted ? 'Yes' : 'No'}</p>
            </div>
        </g:each>
    </div>
</body>
</html>
