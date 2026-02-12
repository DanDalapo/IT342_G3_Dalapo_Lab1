import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import './css/Signup.css';

import { FiMail, FiLock, FiUser, FiCalendar } from 'react-icons/fi';

const Signup = () => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        dateOfBirth: '',
        email: '',
        password: ''
    });
    
    const [message, setMessage] = useState('');

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSignup = async (e) => {
        e.preventDefault();
        setMessage('');

        try {
            await axios.post('http://localhost:8080/users', {
                firstName: formData.firstName,
                lastName: formData.lastName,
                dateOfBirth: formData.dateOfBirth,
                email: formData.email,
                password: formData.password,
                registered: true 
            });
            
            alert('Registration successful! You can now log in.');
            navigate('/'); 

        } catch (error) {
            if (error.response) {
                setMessage(error.response.data || "Registration failed");
            } else {
                setMessage("Server error. Is the backend running?");
            }
        }
    };

    return (
        <div className="signup-container">
            
            <div className="signup-image-panel">
                <div className="art-image"></div>
            </div>

            <div className="signup-form-panel" style={{ overflowY: 'auto' }}>
                <div className="signup-header">
                    <h2>Sign up page</h2>
                </div>
                
                <form onSubmit={handleSignup}>
                    <div style={{ display: 'flex', gap: '10px' }}>
                        <div className="input-group" style={{ flex: 1 }}>
                            <label className="input-label">First Name</label>
                            <div className="input-wrapper">
                                <FiUser className="input-icon" />
                                <input type="text" name="firstName" placeholder="John" className="styled-input" value={formData.firstName} onChange={handleChange} required />
                            </div>
                        </div>

                        <div className="input-group" style={{ flex: 1 }}>
                            <label className="input-label">Last Name</label>
                            <div className="input-wrapper">
                                <FiUser className="input-icon" />
                                <input type="text" name="lastName" placeholder="Doe" className="styled-input" value={formData.lastName} onChange={handleChange} required />
                            </div>
                        </div>
                    </div>

                    <div className="input-group">
                        <label className="input-label">Date of Birth</label>
                        <div className="input-wrapper">
                            <FiCalendar className="input-icon" />
                            <input type="date" name="dateOfBirth" className="styled-input" value={formData.dateOfBirth} onChange={handleChange} required />
                        </div>
                    </div>

                    <div className="input-group">
                        <label className="input-label">Email</label>
                        <div className="input-wrapper">
                            <FiMail className="input-icon" />
                            <input type="email" name="email" placeholder="hello@email.com" className="styled-input" value={formData.email} onChange={handleChange} required />
                        </div>
                    </div>

                    <div className="input-group">
                        <label className="input-label">Password</label>
                        <div className="input-wrapper">
                            <FiLock className="input-icon" />
                            <input type="password" name="password" placeholder="Create a password" className="styled-input" value={formData.password} onChange={handleChange} required />
                        </div>
                    </div>

                    {message && <p style={{color: 'red', fontSize: '0.8rem', marginTop: '5px'}}>{message}</p>}

                    <button type="submit" className="signup-btn">Sign Up</button>
                    
                    <div className="login-link">
                        Already have an account? <Link to="/">Log in</Link>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default Signup;