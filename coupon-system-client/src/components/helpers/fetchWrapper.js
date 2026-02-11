
const login = async (email, password, type) => {

    try{
      const requestOptions = {
                  method: "GET"
                }
                const response = await fetch(`api/${type}/login?email=${email}&password=${password}`, requestOptions);
                console.log(response)
                if (!response.ok) {
                  throw new Error('Somthing went wrong!');
                }
                const isLogin = await response.text();
                if (isLogin){
                  return true;
                } else {
                  return false;
                }
              } catch (error) {
                  console.log(error);
                  
              } 
              return false;
    }

    const getAllCompanies = async () => {
      try{
        const requestOptions = {
                    method: "GET",
                    headers: {'Content-Type': 'application/json', 'token': 'Coupn_project_token_5678'}
                  }
                  const response = await fetch(`api/admin/getAllCompanies`, requestOptions);
                  console.log(response)
                  if (!response.ok) {
                    throw new Error('Somthing went wrong!');
                  }
                  const data = await response.json();
                  const comapnies = data.map(c =>{
                    console.log(c);
                    return {
                      id: c.id,
                      name: c.name,
                      email: c.email,
                      password: c.password
                    }
                  })
                  return comapnies;
                } catch (error) {
                    console.log(error);
                    
                } 
                return [];
    }
  
    const addCompany = async (newCompany) => {
      try{
        const requestOptions = {
            method: "POST",
            headers: {'Content-Type': 'application/json', 'token': 'Coupn_project_token_5678'},
            body: JSON.stringify(newCompany)
          }
          const response = await fetch(`api/admin/addCompany`, requestOptions);
          console.log(response)
          if (response.ok) {
            return true;
          }
            else{
            //throw new Error('Somthing went wrong!');
            return false;
          }
          // const data = await response.text();
          // console.log(data) 
        } catch (error) {
            console.log(error);
            
        } 
      
    }

    const deleteCompany = async (companyId) => {
        try {
          const requestOptions = {
            method: "DELETE",
            headers: {'Content-Type': 'application/json', 'token': 'Coupn_project_token_5678'},
          }
          const response = await fetch(`api/admin/deleteCompany?id=${companyId}`, requestOptions);
          console.log(response)
          
        } catch (error) {
          console.log(error)
        }
    }

const fetchWrapper = {
  login: login,
  getAllCompanies: getAllCompanies,
  addCompany: addCompany,
  deleteCompany: deleteCompany
}


export default fetchWrapper;