import React from 'react';

const AuthContext = React.createContext({
  isLoggedIn: false,
  onLogout: null
});

export default AuthContext;