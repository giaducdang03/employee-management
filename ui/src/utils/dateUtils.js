export function formatDate(dateString) {
  const date = new Date(dateString);
  if (isNaN(date.getTime())) {
    return "Invalid date";
  } else {
    const timezoneOffset = date.getTimezoneOffset() * 60000;
    const localTime = date.getTime() - timezoneOffset;
    const localDate = new Date(localTime);

    const year = localDate.getFullYear();
    const month = (localDate.getMonth() + 1).toString().padStart(2, "0");
    const day = localDate.getDate().toString().padStart(2, "0");
    const hours = localDate.getHours().toString().padStart(2, "0");
    const minutes = localDate.getMinutes().toString().padStart(2, "0");
    const seconds = localDate.getSeconds().toString().padStart(2, "0");
    const milliseconds = localDate
      .getMilliseconds()
      .toString()
      .padStart(3, "0");

    const formattedTimestamp = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}.${milliseconds}`;
    return formattedTimestamp;
  }
}

export function formatTimestamp(timestampStr) {
  if (timestampStr === null || timestampStr === undefined) {
    return "-";
  }
  const timestamp = new Date(timestampStr);
  return timestamp.toLocaleString('en-GB', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric'
  });
}

export function formatTimestampWithHour(timestampStr) {
  if (timestampStr === null || timestampStr === undefined) {
    return "-";
  }
  const timestamp = new Date(timestampStr);
  return timestamp.toLocaleString('en-GB', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
      hour: '2-digit',
      minute: '2-digit',
      second: '2-digit'
  });
}
