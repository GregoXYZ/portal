package common.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Populate {
    public static void populate(Object bean, Map<?, ?> properties) throws InvocationTargetException, IllegalAccessException {
        if ((bean == null) || (properties == null)) {
            return;
        }

        Iterator<?> names = properties.keySet().iterator();
        while (names.hasNext()) {
            // Identify the property name and value(s) to be assigned
            String name = (String) names.next();
            if (name == null) {
                continue;
            }
            Object value = properties.get(name);
            name = "set" + name;

            //Sets the value in the object bean
            MethodDescriptor myDescriptor = getMethodDescriptor(bean, name);
            if(myDescriptor!=null) {
                Method method = myDescriptor.getMethod();
                if(method!=null) {
                    try {
                        method.invoke(bean, new Object[]{value});
                    } catch( InvocationTargetException e ) {
                        e.printStackTrace();
                    } catch( IllegalAccessException e ) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void populate(Object bean, ResultSet rset) {
        if(rset==null || bean==null) return;

        int indice = 1;
        try {
            while(rset.next()) {
                Enumeration<?> columnas = getColumnas(rset);
                Map<?, ?> properties = getValues(rset, columnas);

                //repetido pq el metodo es nombrecolumna+indice...  
                Iterator<?> names = properties.keySet().iterator();
                while (names.hasNext()) {
                    // Identify the property name and value(s) to be assigned
                    String name = (String) names.next();
                    if (name == null) {
                        continue;
                    }
                    Object value = properties.get(name);
                    name = "set"+ name + indice;

                    //Sets the value in the object bean
                    MethodDescriptor myDescriptor = getMethodDescriptor(bean, name);
                    if(myDescriptor!=null) {
                        Method method = myDescriptor.getMethod();
                        if(method!=null) {
                            try {
                                method.invoke(bean, new Object[]{value});
                            } catch( InvocationTargetException e ) {
                                e.printStackTrace();
                            } catch( IllegalAccessException e ) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                indice++;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private static MethodDescriptor getMethodDescriptor(Object bean, String name) {
        MethodDescriptor myDescriptor = null;

        try {
            MethodDescriptor[] descriptors = Introspector.getBeanInfo(bean.getClass()).getMethodDescriptors();

            for(int i=0; i<descriptors.length && myDescriptor==null; i++) {
                if(descriptors[i].getName().equals(name)) {
                    myDescriptor = descriptors[i];
                }
            }
        } catch(IntrospectionException e) {
            e.printStackTrace();
        }

        return myDescriptor;
    }

    private static Enumeration<String> getColumnas(ResultSet rset) {
        ArrayList<String> al = new ArrayList<String>();

        try {
            ResultSetMetaData metaData = rset.getMetaData();
            int numCols = metaData.getColumnCount();
            for(int i=1; i<=numCols; i++) {
                al.add(metaData.getColumnLabel(i));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return Collections.enumeration(al);
    }

    private static Map<String, String> getValues(ResultSet rset, Enumeration<?> columnas) {
        HashMap<String, String> properties = new HashMap<String, String>();

        try {
            if(rset!=null && columnas!=null) {
                while(columnas.hasMoreElements()) {
                    String columna = (String)columnas.nextElement();
                    String valor = rset.getString(columna);
                    properties.put(columna, valor);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
