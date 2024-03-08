import React from 'react';
import Header from './Header';
import { Container, Typography, Card, CardContent, Button } from '@mui/material';

function AdminManageRolesPage() {
  const isAdmin = true;

  // Dummy data for users
  const users = [
    { id: 1, name: 'User 1', role: 'Member' },
    { id: 2, name: 'User 2', role: 'Member' },
    { id: 3, name: 'User 3', role: 'Staff' }
  ];

  if (!isAdmin) {
    return <Typography variant="h5">Access Denied</Typography>;
  }

  
  const promoteToStaff = (userId) => {
    console.log(`Promoting user ${userId} to staff`); // Placeholder logic
  };

  return (
    <div>
      <Header />

      <Container maxWidth="lg">
        <br />
        {/* User Management Section */}
        <section>
          <Typography variant="h4" gutterBottom>
            Manage User Roles
          </Typography>
          <div style={{ display: 'flex', justifyContent: 'space-between', flexWrap: 'wrap' }}>
            {users.map(user => (
              <Card key={user.id} style={{ width: '30%', marginBottom: '20px' }}>
                <CardContent>
                  <Typography variant="h6">{user.name}</Typography>
                  <Typography variant="body1">Role: {user.role}</Typography>
                  <Button variant="contained" color="secondary" onClick={() => promoteToStaff(user.id)}>
                    Promote to Staff
                  </Button>
                </CardContent>
              </Card>
            ))}
          </div>
        </section>
      </Container>
    </div>
  );
}

export default AdminManageRolesPage;
