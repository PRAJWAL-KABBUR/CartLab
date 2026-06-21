import axios from "axios"
import { useState } from "react"
import { useHistory } from "react-router-dom"
import uservalidation from "../uservalidation"

function RegCustomer() {
    const [user, setUser] = useState({
        name: "", city: "", email: "",
        pwd: "", cpwd: "", phone: "", gender: ""
    })
    const [errors, setErrors] = useState({})
    const history = useHistory()

    const handleInput = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value })
    }

    const handleSubmit = async (e) => {
        e.preventDefault()

        // ── Step 1: validate everything synchronously ──────────────
        const validationErrors = uservalidation(user)
        if (!user.gender) {
            validationErrors.gender = "Gender is required"
        }
        setErrors(validationErrors)

        console.log("[DEBUG] Validation errors:", validationErrors)
        console.log("[DEBUG] Payload to be sent:", user)

        if (Object.keys(validationErrors).length > 0) {
            console.log("[DEBUG] Validation failed — API call aborted")
            return
        }

        // ── Step 2: strip cpwd, call API ───────────────────────────
        const { cpwd, ...payload } = user
        console.log("[DEBUG] Calling POST /api/customers with:", payload)

        try {
            const resp = await axios.post("http://localhost:8080/api/customers", payload)
            console.log("[DEBUG] Response status:", resp.status)
            console.log("[DEBUG] Response data:", resp.data)
            alert("Customer registered successfully!")
            history.push("/clogin")
        } catch (error) {
            console.error("[DEBUG] Axios error:", error)
            console.error("[DEBUG] error.response:", error.response)
            console.error("[DEBUG] error.response.status:", error.response?.status)
            console.error("[DEBUG] error.response.data:", error.response?.data)

            if (error.response && error.response.status === 404) {
                alert("This email is already registered!")
            } else if (error.response) {
                alert(`Server error (${error.response.status}): ${JSON.stringify(error.response.data)}`)
            } else {
                alert("Cannot reach the server. Is the backend running on port 8080?")
            }
        }
    }

    return (
        <div className="container">
            <div className="card shadow bg-transparent mt-3 text-white">
                <div className="card-body">
                    <div className="row">
                        <div className="col-sm-6 mx-auto">
                            <h4 className="text-center p-2">Customer Registration Form</h4>
                            <form onSubmit={handleSubmit}>
                                <div className="form-group form-row">
                                    <label className="col-sm-4 form-control-label">Customer Name</label>
                                    <div className="col-sm-8">
                                        <input type="text" name="name" value={user.name} onChange={handleInput} className="form-control" />
                                        {errors.name && <small className="text-danger float-right">{errors.name}</small>}
                                    </div>
                                </div>
                                <div className="form-group form-row">
                                    <label className="col-sm-4 form-control-label">City</label>
                                    <div className="col-sm-8">
                                        <input type="text" name="city" value={user.city} onChange={handleInput} className="form-control" />
                                        {errors.city && <small className="text-danger float-right">{errors.city}</small>}
                                    </div>
                                </div>
                                <div className="form-group form-row">
                                    <label className="col-sm-4 form-control-label">Gender</label>
                                    <div className="col-sm-8">
                                        <select name="gender" value={user.gender} onChange={handleInput} className="form-control">
                                            <option value="">Select Gender</option>
                                            <option>Male</option>
                                            <option>Female</option>
                                        </select>
                                        {errors.gender && <small className="text-danger float-right">{errors.gender}</small>}
                                    </div>
                                </div>
                                <div className="form-group form-row">
                                    <label className="col-sm-4 form-control-label">Email-Id</label>
                                    <div className="col-sm-8">
                                        <input type="email" name="email" value={user.email} onChange={handleInput} className="form-control" />
                                        {errors.email && <small className="text-danger float-right">{errors.email}</small>}
                                    </div>
                                </div>
                                <div className="form-group form-row">
                                    <label className="col-sm-4 form-control-label">Phone</label>
                                    <div className="col-sm-8">
                                        <input type="text" maxLength="10" minLength="10" name="phone" value={user.phone} onChange={handleInput} className="form-control" />
                                        {errors.phone && <small className="text-danger float-right">{errors.phone}</small>}
                                    </div>
                                </div>
                                <div className="form-group form-row">
                                    <label className="col-sm-4 form-control-label">Password</label>
                                    <div className="col-sm-8">
                                        <input type="password" minLength="4" maxLength="10" name="pwd" value={user.pwd} onChange={handleInput} className="form-control" />
                                        {errors.pwd && <small className="text-danger float-right">{errors.pwd}</small>}
                                    </div>
                                </div>
                                <div className="form-group form-row">
                                    <label className="col-sm-4 form-control-label">Confirm Password</label>
                                    <div className="col-sm-8">
                                        <input type="password" name="cpwd" value={user.cpwd} onChange={handleInput} className="form-control" />
                                        {errors.cpwd && <small className="text-danger float-right">{errors.cpwd}</small>}
                                    </div>
                                </div>
                                <button className="btn btn-primary float-right">Register Now</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default RegCustomer