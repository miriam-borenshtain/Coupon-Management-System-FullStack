import React, { useState, useEffect, useCallback } from 'react';

import Login from './components/Login/Login';
import Home from './components/Home/Home';
import MainHeader from './components/MainHeader/MainHeader';
import AuthContext from './store/auth-context';
import fetchWrapper from './components/helpers/fetchWrapper';
import Companies from './components/company/Companies';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [companies, setCompanies] = useState([])

  useEffect(() => {
    const storedUserLoggedInInformation = localStorage.getItem('isLoggedIn');

    if (storedUserLoggedInInformation === '1') {
      setIsLoggedIn(true);
    }
  }, []);

  const loginHandler = useCallback(async (email, password, user_type) => {
    const validLogin = await fetchWrapper.login(email, password, user_type);
    if(validLogin){
      localStorage.setItem('isLoggedIn', '1');
      setIsLoggedIn(true);
      const companies = await fetchWrapper.getAllCompanies();
      setCompanies(companies);
    }
    else{
      console.log("failed to login")
    }
  }, []);

  const logoutHandler = () => {
    localStorage.removeItem('isLoggedIn');
    setIsLoggedIn(false);
  };

  const onSaveCompanyDataHandler = (data) => {
    console.log('details: ' + JSON.stringify(data));
    const newCompany = { id: 'e' + (companies.length + 1), ...data };
    setCompanies((prevCompanies) => ([
      ...prevCompanies,
      newCompany
    ]))
  };

  const onDeleteCompanyHandler = (id) => {
    setCompanies(companies.filter(item => item.id !== id))
  }

  return (
    <AuthContext.Provider
      value={{
        isLoggedIn: isLoggedIn,
        onLogout: logoutHandler
      }}
    >
      <MainHeader />
      <main>
        {!isLoggedIn && <Login onLogin={loginHandler} />}
        {isLoggedIn && <Home onLogout={logoutHandler} />}
        {isLoggedIn &&<Companies items={companies} addCompany={onSaveCompanyDataHandler} deleteCompany={onDeleteCompanyHandler}></Companies>}
      </main>
    </AuthContext.Provider>
  );
}

export default App;
