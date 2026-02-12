import React, { useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import './css/Login.css'; 

import { FiMail, FiLock } from 'react-icons/fi';

const Login = () => {
    const [formData, setFormData] = useState({
        email: '',
        password: ''
    });
    
    const [message, setMessage] = useState('');

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleLogin = async (e) => {
        e.preventDefault();
        setMessage('');

        try {
            const response = await axios.post('http://localhost:8080/api/auth/login', {
                email: formData.email,
                password: formData.password
            });
            
            console.log("Login Success:", response.data);
            
            alert(`Welcome back, ${response.data.firstName}!`);
        } catch (error) {
            if (error.response) {
                setMessage(error.response.data);
            } else {
                setMessage("Server error. Is the backend running?");
            }
        }
    };

    return (
        <div className="login-container">
            <div className="login-left">

                <div className="login-header">
                    <h2>Login page</h2>
                </div>
                
                <form onSubmit={handleLogin}>
                    <div className="input-group">
                        <label className="input-label">Email</label>
                        <div className="input-wrapper">
                            <FiMail className="input-icon" />
                            <input 
                                type="email" 
                                name="email"
                                placeholder="hello@email.com" 
                                className="styled-input"
                                value={formData.email}
                                onChange={handleChange}
                                required
                            />
                        </div>
                    </div>

                    <div className="input-group">
                        <label className="input-label">Password</label>
                        <div className="input-wrapper">
                            <FiLock className="input-icon" />
                            <input 
                                type="password" 
                                name="password"
                                placeholder="Your password" 
                                className="styled-input"
                                value={formData.password}
                                onChange={handleChange}
                                required
                            />
                        </div>
                    </div>

                    {message && <p style={{color: 'red', fontSize: '0.8rem', marginTop: '5px'}}>{message}</p>}

                    <button type="submit" className="login-btn">Login</button>
                    
                    <div className="signup-link">
                        Don't have an account? <Link to="/signup">Sign up</Link>
                    </div>                    
                </form>
            </div>

            <div className="login-right">
                <div className="art-image"></div>
            </div>
        </div>
    );
};

export default Login;