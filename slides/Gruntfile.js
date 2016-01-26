module.exports = function(grunt) {

    grunt.initConfig({
		exec: {
		  readme: 'asciidoctor "README.asc"',            
		  session: 'asciidoctor -T asciidoctor-reveal.js/templates/slim "java-ee-mvc-and-polymer.asc"',
            pdf: 'asciidoctor-pdf *.asc'		  
		},
        'connect': {
            demo: {
                options: {
                    open: true,
                    keepalive: true,
                    port: 9091,
					base: "."	
                }
            }
        }
    });

	grunt.loadNpmTasks('grunt-exec');
    grunt.loadNpmTasks('grunt-contrib-connect');
    grunt.registerTask('server', ['connect']);
    
	grunt.registerTask('build-readme', ['exec:readme']);	
	grunt.registerTask('build-session', ['exec:session']);	
	grunt.registerTask('build-pdf', ['exec:pdf']);		
        
	grunt.registerTask('default', ['build-readme', 'build-session', 'build-pdf']);	
    
};
