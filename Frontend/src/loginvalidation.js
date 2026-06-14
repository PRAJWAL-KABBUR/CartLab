const loginvalidation=(values)=>{
    let errors={}
    if(!values.email){
        errors.email="User id is required"
    }
    if(!values.pwd){
        errors.pwd="Password is required"
    }    
    return errors;
}

export default loginvalidation;