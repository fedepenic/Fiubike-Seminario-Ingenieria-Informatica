<!DOCTYPE html>
<html>
<head>
    <title>Bike Rental</title>
</head>
<body>
    <h1>Bike Rental</h1>
    <p>Please select the time and date you want to start your rental.</p>
    <label>Start of Rental:</label>
    <div>
        <label for="startHour">Hour:</label>
        <g:select name="startHour" from="${(0..23)}" />
        <label for="startMinute">Minute:</label>
        <g:select name="startMinute" from="${(0..59)}" />
        <label for="startDay">Day:</label>
        <g:select name="startDay" from="${(1..31)}" />
        <label for="startMonth">Month:</label>
        <g:select name="startMonth" from="${['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']}" />
        <label for="startYear">Year:</label>
        <g:select name="startYear" from="${(2023..2030)}" />
    </div>
    <br/>
    <p>Please select the time and date you want to end your rental.</p>
    <label>End of Rental:</label>
    <div>
        <label for="endHour">Hour:</label>
        <g:select name="endHour" from="${(0..23)}" />
        <label for="endMinute">Minute:</label>
        <g:select name="endMinute" from="${(0..59)}" />
        <label for="endDay">Day:</label>
        <g:select name="endDay" from="${(1..31)}" />
        <label for="endMonth">Month:</label>
        <g:select name="endMonth" from="${['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']}" />
        <label for="endYear">Year:</label>
        <g:select name="endYear" from="${(2023..2030)}" />
    </div>
</body>
</html>
