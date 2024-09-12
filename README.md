<h1>Chat Application Backend</h1>

<h2>Tech Stack:</h2>
<ul>
  <li>Java 21</li>
  <li>Spring Boot 3.3.3</li>
  <li>MongoDB</li>
  <li>WebSockets</li>
  <li>Google Cloud (Deployment)</li>
</ul>

<h2>Project Overview:</h2>
<p>
  This is the backend of a real-time chat application, developed using Spring Boot and MongoDB, utilizing WebSockets to enable instant messaging between users. 
  Users can create group chats, send messages, and get notifications about message reads, new chats, and more. The backend is responsible for routing notifications and managing chat data.
</p>

<h2>Features:</h2>

<h3>WebSocket-based Messaging:</h3>
<ul>
  <li>Users subscribe to their own WebSocket endpoint (<code>/ws/queue/{userId}</code>) to receive real-time messages and notifications.</li>
</ul>

<h3>Notification System:</h3>
<ul>
  <li>Users can send different types of notifications (e.g., <code>MSG_SENT</code>, <code>MSG_READ</code>, <code>CHAT_CREATED</code>) via the <code>/ws/sendNotification</code> endpoint.</li>
  <li>Notifications are processed and routed to users, including updates on new messages, message read statuses, and group chat creation.</li>
</ul>

<h3>Group Chat:</h3>
<ul>
  <li>Users can create group chats and send messages to groups or individual users.</li>
</ul>

<h3>Message Read Status:</h3>
<ul>
  <li>Each message contains a map of read statuses (&lt;userId, timestamp&gt;), allowing users to see when each message is read by others.</li>
</ul>

<h2>Endpoints:</h2>

<h3>WebSocket Subscription Endpoint:</h3>
<ul>
  <li><code>/ws/queue/{userId}</code>: Users subscribe to this endpoint to receive real-time messages and notifications.</li>
</ul>

<h3>Notification Sending Endpoint:</h3>
<ul>
  <li><code>/ws/sendNotification</code>: Send notifications, including message sends, reads, and new group chat creation.</li>
</ul>

<h2>Message Handling Logic:</h2>

<h3>Real-time Messaging:</h3>
<ul>
  <li>When a user sends a message, the backend pushes the message to all users in the chat via their WebSocket subscriptions.</li>
</ul>

<h3>Read Receipts:</h3>
<ul>
  <li>Message read status is stored in a map within each message, tracking when users have viewed the message.</li>
</ul>

<h3>User Information:</h3>
<ul>
  <li>Users are identified by their MongoDB ObjectIDs, and their username data is fetched on login to map ID to name in group chats and messages.</li>
</ul>
